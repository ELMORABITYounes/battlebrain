package com.elmorabit.battlebrain.service;

import com.elmorabit.battlebrain.service.dto.BookingDTO;
import com.elmorabit.battlebrain.service.dto.SeatDTO;

import java.util.List;

/**
 * Service Interface for managing {@link com.elmorabit.battlebrain.domain.Reservation}.
 */
public interface BookingService {

    /**
     * Get all the reservations.
     *
     * @return the list of entities.
     */
    List<SeatDTO> getAllAvailableSeats(BookingDTO bookingDTO);
}
