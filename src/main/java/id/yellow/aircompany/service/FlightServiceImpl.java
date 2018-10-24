package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.FlightConverter;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.FlightModelForCreating;
import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.repository.FlightRepository;
import id.yellow.aircompany.utility.DiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private DiscountCalculator discountCalculator;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<FlightModelForUser> getFlights(int page, int pageSize, LocalDate dateFrom, LocalDate dateTo,
                                               String destFrom, String destTo, double priceFrom, double priceTo,
                                               String sortByDate, String sortByPrice) {

        String dateFromString = dateFrom == null ? null :
                dateFrom.atStartOfDay().toInstant(ZoneOffset.UTC).toString();

        String dateToString = dateTo == null ? null :
                dateTo.atStartOfDay().toInstant(ZoneOffset.UTC).toString();

        List<FlightModelForUser> flightModelsForUser = FlightConverter.toFlightModelsForUser(
                flightRepository.findAllByParameters(dateFromString, dateToString,
                destFrom, destTo, priceFrom, priceTo, PageRequest.of(page - 1, pageSize)).getContent());

        return discountCalculator.calculateDiscounts(flightModelsForUser);
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public FlightModelForUser getFlightById(long flightId) {

        FlightEntity flightEntity = flightRepository.findOneById(flightId);

        if(flightEntity != null) {
            return discountCalculator.calculateDiscount(FlightConverter.toFlightModelForUser(flightEntity));
        }

        throw new NotFoundException("Flight with this id is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public FlightModelForCreating createFlight(FlightModelForCreating flightModelForCreating) {

        return FlightConverter.toFlightModelForCreating(flightRepository
                .save(FlightConverter.toFlightEntity(flightModelForCreating)));
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public FlightModelForCreating updateFlight(long flightId, FlightModelForCreating flightModelForCreating) {

        FlightEntity flightEntity = flightRepository.findOneById(flightId);

        if(flightEntity != null) {
            flightEntity.setSerialNumber(flightModelForCreating.getSerialNumber());
            flightEntity.setDateTime(flightModelForCreating.getDateTime());
            flightEntity.setDestinationFrom(flightModelForCreating.getDestinationFrom());
            flightEntity.setDestinationTo(flightModelForCreating.getDestinationTo());
            flightEntity.setPrice(flightModelForCreating.getPrice());
            flightEntity.setNumOfSeats(flightModelForCreating.getNumOfSeats());

            return FlightConverter.toFlightModelForCreating(flightRepository.save(flightEntity));
        }

        throw new NotFoundException("Flight with this id is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public void deleteFlight(long flightId) {

        FlightEntity flightEntity = flightRepository.findOneById(flightId);

        if(flightEntity != null) {
            flightRepository.deleteOneById(flightId);
        } else {
            throw new NotFoundException("Flight is not found!");
        }
    }
}
