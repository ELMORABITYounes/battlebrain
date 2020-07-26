package com.elmorabit.battlebrain.web.rest;

import com.elmorabit.battlebrain.service.BookingService;
import com.elmorabit.battlebrain.service.dto.BookingDTO;
import com.elmorabit.battlebrain.service.dto.SeatDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link com.elmorabit.battlebrain.domain.Reservation}.
 */
@RestController
@RequestMapping("/api")
public class BookingController {

    private final Logger log = LoggerFactory.getLogger(BookingController.class);

    private static final String ENTITY_NAME = "reservation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * {@code POST  /reservations} : Create a new reservation.
     *
     * @param bookingDTO the bookingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookingDTO, or with status {@code 400 (Bad Request)} if the reservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/booking")
    public ResponseEntity<List<SeatDTO>> createReservation(@Valid @RequestBody BookingDTO bookingDTO) throws URISyntaxException {
        log.debug("REST request to save Reservation : {}", bookingDTO);
        final Instant startDate = bookingDTO.getStartDate();
        final Instant endDate = bookingDTO.getEndDate();
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.compareTo(Instant.now()) <= 0 || startDate.compareTo(endDate) >= 0) {
            throw new ValidationException("invalid data");
        }
        List<SeatDTO> result = bookingService.getAllAvailableSeats(bookingDTO);
        return ResponseEntity.ok(result);
    }
}
