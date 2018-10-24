package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.FlightDiscountModel;
import id.yellow.aircompany.service.FlightDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class FlightDiscountController {

    @Autowired
    private FlightDiscountService flightDiscountService;

    @GetMapping("/discounts")
    public List<FlightDiscountModel> getFlightDiscounts(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                        @RequestParam(name = "valueFrom", required = false) Integer valueFrom) {

        return flightDiscountService.getFlightDiscounts(page, pageSize, valueFrom);
    }

    @GetMapping("/discounts/{flightDiscountId}")
    public FlightDiscountModel getFlightDiscount(@PathVariable long flightDiscountId) {

        return flightDiscountService.getFlightDiscount(flightDiscountId);
    }

    @PostMapping("/discounts")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Long> createFlightDiscount(@RequestParam(name = "value") int value,
                                                    @RequestParam(name = "dateFrom")
                                                        @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateFrom,
                                                    @RequestParam(name = "dateTo")
                                                        @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dateTo,
                                                    @RequestParam(name = "flightIds",
                                                            required = false) List<Long> flightIds) {

        return flightDiscountService.createFlightDiscount(value, dateFrom, dateTo, flightIds);
    }

    @DeleteMapping("/discounts/{flightDiscountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightDiscount(@PathVariable long flightDiscountId) {

        flightDiscountService.deleteFlightDiscount(flightDiscountId);
    }
}
