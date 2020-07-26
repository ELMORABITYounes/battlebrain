package com.elmorabit.battlebrain.service.impl;

import com.elmorabit.battlebrain.domain.Reservation;
import com.elmorabit.battlebrain.domain.Seat;
import com.elmorabit.battlebrain.repository.ReservationRepository;
import com.elmorabit.battlebrain.repository.SeatRepository;
import com.elmorabit.battlebrain.service.BookingService;
import com.elmorabit.battlebrain.service.dto.BookingDTO;
import com.elmorabit.battlebrain.service.dto.SeatDTO;
import com.elmorabit.battlebrain.service.mapper.ReservationMapper;
import com.elmorabit.battlebrain.service.mapper.SeatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Reservation}.
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    private final SeatRepository seatRepository;

    private final SeatMapper seatMapper;


    public BookingServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, final SeatRepository seatRepository, final SeatMapper seatMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    @Override
    public List<SeatDTO> getAllAvailableSeats(final BookingDTO bookingDTO) {


        List<Seat> seatList;

        if (Objects.nonNull(bookingDTO.getAreaId())) {
            seatList = seatRepository.findAllByAreaId(bookingDTO.getAreaId());
        } else {
            seatList = seatRepository.findAll();
        }

        return seatList.stream().map(seatMapper::toDto).collect(Collectors.toList());
    }
}
