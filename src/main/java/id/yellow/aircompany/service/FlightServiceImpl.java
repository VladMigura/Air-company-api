package id.yellow.aircompany.service;

import id.yellow.aircompany.model.FlightModel;
import id.yellow.aircompany.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<FlightModel> getFlights(int page, int pageSize, String dateFrom, String dateTo,
                                        String destFrom, String destTo, BigDecimal priceFrom, BigDecimal priceTo) {


        return null;
    }

    @Override
    public long createFlight(FlightModel flightModel) {
        return 0;
    }

    @Override
    public void deleteFlight(String flightSerialNumber) {

    }
}
