package id.yellow.aircompany.controller;

import id.yellow.aircompany.model.FlightDiscountModel;
import id.yellow.aircompany.service.FlightDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightDiscountController {

    @Autowired
    private FlightDiscountService flightDiscountService;

    @GetMapping("/discounts")
    public List<FlightDiscountModel> getFlightDiscounts(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                        @RequestParam(name = "valueTo", required = false) int valueTo) {

        return flightDiscountService.getFlightDiscounts(page, pageSize, valueTo);
    }

    @GetMapping("/discount/{id}")
    public FlightDiscountModel getFlightDiscount(@PathVariable long id) {

        return flightDiscountService.getFlightDiscount(id);
    }

    @PostMapping("/discount")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDiscountModel createFlightDiscount(@RequestBody FlightDiscountModel flightDiscountModel) {
        return null;
    }

    @DeleteMapping("/discounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlightDiscount(@PathVariable long id) {

        flightDiscountService.deleteFlightDiscount(id);
    }
}
