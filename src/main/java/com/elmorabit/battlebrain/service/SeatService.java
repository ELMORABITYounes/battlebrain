package com.elmorabit.battlebrain.service;

import com.elmorabit.battlebrain.service.dto.SeatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.elmorabit.battlebrain.domain.Seat}.
 */
public interface SeatService {

    /**
     * Save a seat.
     *
     * @param seatDTO the entity to save.
     * @return the persisted entity.
     */
    SeatDTO save(SeatDTO seatDTO);

    /**
     * Get all the seats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SeatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" seat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SeatDTO> findOne(Long id);

    /**
     * Delete the "id" seat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
