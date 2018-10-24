package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.WaitingRecordConverter;
import id.yellow.aircompany.entity.WaitingRecordEntity;
import id.yellow.aircompany.exception.ForbiddenException;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.WaitingRecordModel;
import id.yellow.aircompany.repository.WaitingListRepository;
import id.yellow.aircompany.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListServiceImpl implements WaitingListService {

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<WaitingRecordModel> getWaitingList(Long userId, int page, int pageSize) {

        if(SecurityUtility.isAdmin()) {
            if(userId == null) {
                return WaitingRecordConverter.toWaitingRecordModels(waitingListRepository
                        .findAll(PageRequest.of(page, pageSize)).getContent());
            }

            return WaitingRecordConverter.toWaitingRecordModels(waitingListRepository
                        .findAllByUserId(userId, PageRequest.of(page, pageSize)).getContent());
        }

        userId = SecurityUtility.getUserId();

        return WaitingRecordConverter.toWaitingRecordModels(waitingListRepository
                .findAllByUserId(userId, PageRequest.of(page - 1, pageSize)).getContent());
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public void deleteWaitingRecord(long waitingRecordId) {

        WaitingRecordEntity waitingRecordEntity = waitingListRepository.findOneById(waitingRecordId);

        if(waitingRecordEntity != null) {
            if(SecurityUtility.isAdmin()) {
                waitingListRepository.deleteOneById(waitingRecordId);
            } else if(waitingRecordEntity.getUserId() == SecurityUtility.getUserId()) {
                waitingListRepository.deleteOneById(waitingRecordId);
            } else {
                throw new ForbiddenException("You can't delete this item!");
            }
        } else {
            throw new NotFoundException("Waiting record is not found!");
        }
    }
}
