package com.elmorabit.battlebrain.service;

import com.elmorabit.battlebrain.service.dto.AreaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.elmorabit.battlebrain.domain.Area}.
 */
public interface AreaService {

    /**
     * Save a area.
     *
     * @param areaDTO the entity to save.
     * @return the persisted entity.
     */
    AreaDTO save(AreaDTO areaDTO);

    /**
     * Get all the areas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AreaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" area.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AreaDTO> findOne(Long id);

    /**
     * Delete the "id" area.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
