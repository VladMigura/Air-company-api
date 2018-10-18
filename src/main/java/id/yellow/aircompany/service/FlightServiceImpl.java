package id.yellow.aircompany.service;

import com.sun.xml.internal.bind.v2.TODO;
import id.yellow.aircompany.converter.FlightConverter;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.FlightModelForCreating;
import id.yellow.aircompany.model.FlightModelForUser;
import id.yellow.aircompany.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<FlightModelForUser> getFlights(int page, int pageSize, LocalDate dateFrom, LocalDate dateTo,
                                               String destFrom, String destTo, BigDecimal priceFrom, BigDecimal priceTo,
                                               String sortByDate, String sortByPrice) {

        String dateFromString = dateFrom == null ? null :
                dateFrom.atStartOfDay().toInstant(ZoneOffset.UTC).toString();

        String dateToString = dateTo == null ? null :
                dateTo.atStartOfDay().toInstant(ZoneOffset.UTC).toString();

        Pageable pageable = PageRequest.of(page - 1, pageSize);

        List<FlightEntity> flightEntities = flightRepository.findAllByParameters(dateFromString, dateToString,
                destFrom, destTo, priceFrom.doubleValue(), priceTo.doubleValue(), pageable).getContent();

        return FlightConverter.toFlightModelsForUser(flightEntities);
    }

    //TODO Add discount calculator
    @Override
    public FlightModelForUser getFlightById(long id) {

        FlightEntity flightEntity = flightRepository.findOneById(id);

        if(flightEntity != null) {
            return FlightConverter.toFlightModelForUser(flightEntity);
        }

        throw new NotFoundException("Flight with this serial number is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public FlightModelForCreating createFlight(FlightModelForCreating flightModelForCreating) {

        flightRepository.save(FlightConverter.toFlightEntity(flightModelForCreating));

        return flightModelForCreating;
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public FlightModelForCreating updateFlight(long id, FlightModelForCreating flightModelForCreating) {

        FlightEntity flightEntity = flightRepository.findOneById(id);

        if(flightEntity != null) {
            flightEntity.setSerialNumber(flightModelForCreating.getSerialNumber());
            flightEntity.setDateTime(flightModelForCreating.getDateTime());
            flightEntity.setDestinationFrom(flightModelForCreating.getDestinationFrom());
            flightEntity.setDestinationTo(flightModelForCreating.getDestinationTo());
            flightEntity.setPrice(flightModelForCreating.getPrice());
            flightEntity.setNumOfSeats(flightModelForCreating.getNumOfSeats());

            flightRepository.save(flightEntity);

            return flightModelForCreating;
        }

        throw new NotFoundException("Flight with this serial number is not found!");
    }

    @Override
    public void deleteFlight(long id) {

        FlightEntity flightEntity = flightRepository.findOneById(id);

        if(flightEntity != null) {
            flightRepository.deleteOneById(id);
        } else {
            throw new NotFoundException("Flight is not found!");
        }
    }
}
