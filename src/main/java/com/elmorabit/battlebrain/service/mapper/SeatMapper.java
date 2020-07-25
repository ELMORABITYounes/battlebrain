package com.elmorabit.battlebrain.service.mapper;


import com.elmorabit.battlebrain.domain.*;
import com.elmorabit.battlebrain.service.dto.SeatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Seat} and its DTO {@link SeatDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaMapper.class})
public interface SeatMapper extends EntityMapper<SeatDTO, Seat> {

    @Mapping(source = "rightSeat.id", target = "rightSeatId")
    @Mapping(source = "rightSeat.number", target = "rightSeatNumber")
    @Mapping(source = "leftSeat.id", target = "leftSeatId")
    @Mapping(source = "leftSeat.number", target = "leftSeatNumber")
    @Mapping(source = "frontSeat.id", target = "frontSeatId")
    @Mapping(source = "frontSeat.number", target = "frontSeatNumber")
    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "area.name", target = "areaName")
    SeatDTO toDto(Seat seat);

    @Mapping(source = "rightSeatId", target = "rightSeat")
    @Mapping(source = "leftSeatId", target = "leftSeat")
    @Mapping(source = "frontSeatId", target = "frontSeat")
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "removeReservation", ignore = true)
    @Mapping(source = "areaId", target = "area")
    Seat toEntity(SeatDTO seatDTO);

    default Seat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Seat seat = new Seat();
        seat.setId(id);
        return seat;
    }
}
