package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.model.SubscribeModel;

import java.util.List;

public interface SubscribeService {

    List<SubscribeModel> getSubscribes(Long userId);
    List<FlightModelForUser> getFlightsBySubscribe(int page, int pageSize, Long userId, long subscribeId);
    SubscribeModel createSubscribe(String destFrom, String destTo);
    void deleteSubscribe(long subscribeId);
}
