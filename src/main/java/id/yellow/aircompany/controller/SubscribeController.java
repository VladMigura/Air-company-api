package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.model.SubscribeModel;
import id.yellow.aircompany.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @GetMapping("/subscribes")
    public List<SubscribeModel> getSubscribes(@RequestParam(name = "userId", required = false) Long userId) {

        return subscribeService.getSubscribes(userId);
    }

    @GetMapping("/subscribes/{subscribeId}")
    public List<FlightModelForUser> getFlightsBySubscribe(@RequestParam(name = "page", required = false,
                                                                  defaultValue = "1") int page,
                                                          @RequestParam(name = "pageSize", required = false,
                                                                  defaultValue = "20") int pageSize,
                                                          @RequestParam(name = "userId", required = false) Long userId,
                                                          @PathVariable("subscribeId") long subscribeId) {

        return subscribeService.getFlightsBySubscribe(page, pageSize, userId, subscribeId);
    }

    @PostMapping("/subscribes")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscribeModel createSubscribe(@RequestParam(name = "destFrom", required = false) String destFrom,
                                          @RequestParam(name = "destTo", required = false) String destTo) {

        return subscribeService.createSubscribe(destFrom, destTo);
    }

    @DeleteMapping("/subscribes/{subscribeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscribe(@PathVariable("subscribeId") long subscribeId) {

        subscribeService.deleteSubscribe(subscribeId);
    }
}
