package id.yellow.aircompany.service;

import id.yellow.aircompany.model.WaitingRecordModel;

import java.util.List;

public interface WaitingListService {


    List<WaitingRecordModel> getWaitingList(Long userId, int page, int pageSize);
    void deleteWaitingRecord(long waitingRecordId);
}
