package com.elmorabit.battlebrain.service.mapper;


import com.elmorabit.battlebrain.domain.*;
import com.elmorabit.battlebrain.service.dto.ReservationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservation} and its DTO {@link ReservationDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, SeatMapper.class})
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {

    @Mapping(source = "collaborator.id", target = "collaboratorId")
    @Mapping(source = "collaborator.login", target = "collaboratorLogin")
    @Mapping(source = "seat.id", target = "seatId")
    @Mapping(source = "seat.number", target = "seatNumber")
    ReservationDTO toDto(Reservation reservation);

    @Mapping(source = "collaboratorId", target = "collaborator")
    @Mapping(source = "seatId", target = "seat")
    Reservation toEntity(ReservationDTO reservationDTO);

    default Reservation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(id);
        return reservation;
    }
}
