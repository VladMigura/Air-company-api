package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.WaitingRecordModel;
import id.yellow.aircompany.service.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;

    @GetMapping("/waiting-records")
    public List<WaitingRecordModel> getWaitingListByUserId(@RequestParam(name = "page", required = false,
                                                                        defaultValue = "1") int page,
                                                           @RequestParam(name = "pageSize", required = false,
                                                                        defaultValue = "20") int pageSize,
                                                           @RequestParam(name = "userId", required = false) Long userId) {

        return waitingListService.getWaitingList(userId, page, pageSize);
    }

    @DeleteMapping("/waiting-records/{waitingRecordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWaitingRecord(@PathVariable(name = "waitingRecordId") long waitingRecordId) {

        waitingListService.deleteWaitingRecord(waitingRecordId);
    }
}
