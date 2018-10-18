package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.FlightDiscountConverter;
import id.yellow.aircompany.entity.FlightDiscountEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.FlightDiscountModel;
import id.yellow.aircompany.repository.FlightDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightDiscountServiceImpl implements FlightDiscountService {

    @Autowired
    private FlightDiscountRepository flightDiscountRepository;

    @Override
    public List<FlightDiscountModel> getFlightDiscounts(int page, int pageSize, int valueTo) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return FlightDiscountConverter.toFlightDiscountModels(flightDiscountRepository
                .findAllByParameters(valueTo, pageable).getContent());
    }

    @Override
    public FlightDiscountModel getFlightDiscount(long id) {

        return FlightDiscountConverter.toFlightDiscountModel(flightDiscountRepository
                .findOneById(id));
    }

    @Override
    public void deleteFlightDiscount(long id) {

        FlightDiscountEntity flightDiscountEntity = flightDiscountRepository.findOneById(id);

        if(flightDiscountEntity != null) {
            flightDiscountRepository.deleteOneById(id);
        } else {
            throw new NotFoundException("Flight discount is not found!");
        }
    }
}
