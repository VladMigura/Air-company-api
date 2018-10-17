package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.FlightModel;
import id.yellow.aircompany.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<FlightModel> getFlights(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
                                        @RequestParam(name = "dateFrom", required = false) String dateFrom,
                                        @RequestParam(name = "dateTo", required = false) String dateTo,
                                        @RequestParam(name = "destFrom", required = false) String destFrom,
                                        @RequestParam(name = "destTo", required = false) String destTo,
                                        @RequestParam(name = "priceFrom", required = false) BigDecimal priceFrom,
                                        @RequestParam(name = "priceTo", required = false) BigDecimal priceTo) {

        return null;
    }

    @PostMapping("/flights")
    public void createFlight() {


    }

    @PutMapping("/flights")
    public void updateFlight() {


    }
}
