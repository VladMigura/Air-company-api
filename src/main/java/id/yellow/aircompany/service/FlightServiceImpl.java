package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.FlightConverter;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.FlightModel;
import id.yellow.aircompany.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<FlightModel> getFlights(int page, int pageSize, LocalDate dateFrom, LocalDate dateTo,
                                        String destFrom, String destTo, BigDecimal priceFrom, BigDecimal priceTo,
                                        String sortByDate, String sortByPrice) {

        String dateFromString = dateFrom == null ? null :
                dateFrom.atStartOfDay().toInstant(ZoneOffset.UTC).toString();

        String dateToString = dateTo == null ? null :
                dateTo.atStartOfDay().toInstant(ZoneOffset.UTC).toString();

        Sort.Order dateTimeOrder = new Sort.Order(sortByDate.equals("ASC") ? Sort.Direction.ASC :
                Sort.Direction.DESC, "date_time");
        Sort.Order priceOrder = new Sort.Order(sortByPrice.equals("ASC") ? Sort.Direction.ASC :
                Sort.Direction.DESC, "price");

        Pageable pageable = PageRequest.of(page - 1, pageSize, new Sort(Sort.Direction.ASC, "price"));

        List<FlightEntity> flightEntities = flightRepository.findAllByParameters(
                dateFromString,
                dateToString,
                destFrom,
                destTo,
                priceFrom.doubleValue(),
                priceTo.doubleValue(),
                pageable).getContent();

        return FlightConverter.toFlightModels(flightEntities);
    }

    @Override
    public FlightModel getFlightById(long id) {

        FlightEntity flightEntity = flightRepository.findOneById(id);

        if(flightEntity != null) {
            return FlightConverter.toFlightModel(flightEntity);
        }

        throw new NotFoundException("Flight with this serial number is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public FlightModel createFlight(FlightModel flightModel) {

        flightRepository.save(FlightConverter.toFlightEntity(flightModel));

        return flightModel;
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public FlightModel updateFlight(long id, FlightModel flightModel) {

        FlightEntity flightEntity = flightRepository.findOneById(id);

        if(flightEntity != null) {
            flightEntity.setSerialNumber(flightModel.getSerialNumber());
            flightEntity.setDateTime(flightModel.getDateTime());
            flightEntity.setDestinationFrom(flightModel.getDestinationFrom());
            flightEntity.setDestinationTo(flightModel.getDestinationTo());
            flightEntity.setPrice(flightModel.getPrice());
            flightEntity.setNumOfSeats(flightModel.getNumOfSeats());

            flightRepository.save(flightEntity);

            return flightModel;
        }

        throw new NotFoundException("Flight with this serial number is not found!");
    }

    @Override
    public void deleteFlight(long id) {

        FlightEntity flightEntity = flightRepository.findOneById(id);

        if(flightEntity != null) {
            flightRepository.deleteOneBySerialNumber(id);
        } else {
            throw new NotFoundException("Flight with this serial number is not found!");
        }
    }
}
