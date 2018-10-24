package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.FlightModelForCreating;
import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<FlightModelForUser> getFlights(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
                                               @RequestParam(name = "dateFrom", required = false)
                                                   @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateFrom,
                                               @RequestParam(name = "dateTo", required = false)
                                                   @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateTo,
                                               @RequestParam(name = "destFrom", required = false) String destFrom,
                                               @RequestParam(name = "destTo", required = false) String destTo,
                                               @RequestParam(name = "priceFrom", required = false, defaultValue = "-1") double priceFrom,
                                               @RequestParam(name = "priceTo", required = false, defaultValue = "-1") double priceTo,
                                               @RequestParam(name = "sortByDate", required = false, defaultValue = "ASC") String sortByDate,
                                               @RequestParam(name = "sortByPrice", required = false, defaultValue = "ASC") String sortByPrice) {

        return flightService.getFlights(page, pageSize, dateFrom, dateTo, destFrom, destTo,
                                        priceFrom, priceTo, sortByDate, sortByPrice);
    }

    @GetMapping("/flights/{flightId}")
    public FlightModelForUser getFlightById(@PathVariable long flightId) {

        return flightService.getFlightById(flightId);
    }

    @PostMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightModelForCreating createFlight(@RequestBody FlightModelForCreating flightModelForCreating) {

        return flightService.createFlight(flightModelForCreating);
    }

    @PutMapping("/flights/{flightId}")
    public FlightModelForCreating updateFlight(@PathVariable long flightId,
                                               @RequestBody FlightModelForCreating flightModelForCreating) {

        return flightService.updateFlight(flightId, flightModelForCreating);
    }

    @DeleteMapping("/flights/{flightId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable long flightId) {

        flightService.deleteFlight(flightId);
    }
}
