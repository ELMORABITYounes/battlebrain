package com.elmorabit.battlebrain.service;

import com.elmorabit.battlebrain.service.dto.TeamDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.elmorabit.battlebrain.domain.Team}.
 */
public interface TeamService {

    /**
     * Save a team.
     *
     * @param teamDTO the entity to save.
     * @return the persisted entity.
     */
    TeamDTO save(TeamDTO teamDTO);

    /**
     * Get all the teams.
     *
     * @return the list of entities.
     */
    List<TeamDTO> findAll();


    /**
     * Get the "id" team.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TeamDTO> findOne(Long id);

    /**
     * Delete the "id" team.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
