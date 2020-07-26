package com.elmorabit.battlebrain.repository;

import com.elmorabit.battlebrain.domain.Seat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Seat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllByAreaId(final Long area_id);
}
