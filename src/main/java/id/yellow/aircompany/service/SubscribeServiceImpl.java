package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.FlightConverter;
import id.yellow.aircompany.converter.SubscribeConverter;
import id.yellow.aircompany.entity.SubscribeEntity;
import id.yellow.aircompany.entity.UserEntity;
import id.yellow.aircompany.exception.BadRequestException;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.model.SubscribeModel;
import id.yellow.aircompany.repository.FlightRepository;
import id.yellow.aircompany.repository.SubscribeRepository;
import id.yellow.aircompany.utility.DiscountCalculator;
import id.yellow.aircompany.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DiscountCalculator discountCalculator;

    @Override
    @PreAuthorize("@securityUtility.isUser()")
    public SubscribeModel createSubscribe(String destFrom, String destTo) {

        if(destFrom != null || destTo != null) {

            long userId = SecurityUtility.getUserId();

            SubscribeEntity subscribeEntity = SubscribeEntity.builder()
                                                .destinationFrom(destFrom)
                                                .destinationTo(destTo)
                                                .userEntity(UserEntity.builder().id(userId).build())
                                                .build();

            return SubscribeConverter.toSubscribeModel(subscribeRepository.save(subscribeEntity));
        }

        throw new BadRequestException("At least one parameter is required!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<SubscribeModel> getSubscribes(Long userId) {

        if(SecurityUtility.isAdmin()) {
            if(userId != null) {
                return SubscribeConverter.toSubscribeModels(subscribeRepository.findAllByUserId(userId));
            }
            throw new BadRequestException("Parameter 'userId' is missing!");
        }

        userId = SecurityUtility.getUserId();

        return SubscribeConverter.toSubscribeModels(subscribeRepository.findAllByUserId(userId));
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<FlightModelForUser> getFlightsBySubscribe(int page, int pageSize, Long userId, long subscribeId) {

        SubscribeEntity subscribeEntity = subscribeRepository.findOneById(subscribeId);

        if(subscribeEntity != null && (SecurityUtility.isAdmin() ||
                subscribeEntity.getUserId() == SecurityUtility.getUserId())) {

            List<FlightModelForUser> flightModelsForUser = FlightConverter.toFlightModelsForUser(flightRepository
                    .findAllBySubscribe(subscribeEntity.getDestinationFrom(), subscribeEntity.getDestinationTo(),
                            PageRequest.of(page - 1, pageSize)).getContent());

            return discountCalculator.calculateDiscounts(flightModelsForUser);
        } else {
            throw new NotFoundException("Subscribe is not found!");
        }
    }

    @Override
    @PreAuthorize("@securityUtility.isUser()")
    public void deleteSubscribe(long subscribeId) {

        SubscribeEntity subscribeEntity = subscribeRepository.findOneById(subscribeId);

        if(subscribeEntity != null && subscribeEntity.getUserId() == SecurityUtility.getUserId()) {
            subscribeRepository.deleteOneById(subscribeId);
        } else {
            throw new NotFoundException("Subscribe is not found!");
        }
    }
}
