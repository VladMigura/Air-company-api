package id.yellow.aircompany.service;

import id.yellow.aircompany.converter.FlightDiscountConverter;
import id.yellow.aircompany.entity.FlightDiscountEntity;
import id.yellow.aircompany.entity.FlightEntity;
import id.yellow.aircompany.exception.NotFoundException;
import id.yellow.aircompany.model.FlightDiscountModel;
import id.yellow.aircompany.repository.FlightDiscountRepository;
import id.yellow.aircompany.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightDiscountServiceImpl implements FlightDiscountService {

    private final int PAGE_SIZE = 100;

    @Autowired
    private FlightDiscountRepository flightDiscountRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<FlightDiscountModel> getFlightDiscounts(int page, int pageSize, Integer valueFrom) {

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return FlightDiscountConverter.toFlightDiscountModels(flightDiscountRepository
                .findAllByParameters(valueFrom, pageable).getContent());
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public FlightDiscountModel getFlightDiscount(long flightDiscountId) {

        return FlightDiscountConverter.toFlightDiscountModel(flightDiscountRepository
                .findOneById(flightDiscountId));
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public List<Long> createFlightDiscount(int value, LocalDate dateFrom, LocalDate dateTo, List<Long> flightIds) {

        int totalPages = (flightIds == null) ?
                flightRepository.findAll(PageRequest.of(0, PAGE_SIZE)).getTotalPages() :
                flightRepository.findAllByIds(flightIds, PageRequest.of(0, PAGE_SIZE)).getTotalPages();

        List<Long> allIds = new ArrayList<>();
        for (int i = 0; i < totalPages; i++) {
            List<FlightEntity> flightEntities = (flightIds == null) ?
                    flightRepository.findAll(PageRequest.of(i, PAGE_SIZE)).getContent() :
                    flightRepository.findAllByIds(flightIds, PageRequest.of(i, PAGE_SIZE)).getContent();

            List<Long> savedIds = flightEntities
                    .stream()
                    .map(flightEntity -> buildDiscount(value, dateFrom, dateTo, flightEntity))
                    .peek(discount -> flightDiscountRepository.save(discount))
                    .map(FlightDiscountEntity::getId)
                    .collect(Collectors.toList());

            allIds.addAll(savedIds);
        }

        return allIds;
    }

    private FlightDiscountEntity buildDiscount(int value, LocalDate dateFrom,
                                               LocalDate dateTo, FlightEntity flightEntity) {
        return FlightDiscountEntity.builder()
                .value(BigDecimal.valueOf(value))
                .flightEntity(flightEntity)
                .fromDate(dateFrom.atStartOfDay().toInstant(ZoneOffset.UTC))
                .toDate(dateTo.atStartOfDay().toInstant(ZoneOffset.UTC))
                .build();
    }

    @Override
    @PreAuthorize("@securityUtility.isAdmin()")
    public void deleteFlightDiscount(long flightDiscountId) {

        FlightDiscountEntity flightDiscountEntity = flightDiscountRepository.findOneById(flightDiscountId);

        if (flightDiscountEntity != null) {
            flightDiscountRepository.deleteOneById(flightDiscountId);
        } else {
            throw new NotFoundException("Flight discount is not found!");
        }
    }
}
