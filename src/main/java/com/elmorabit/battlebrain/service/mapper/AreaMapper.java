package com.elmorabit.battlebrain.service.mapper;


import com.elmorabit.battlebrain.domain.*;
import com.elmorabit.battlebrain.service.dto.AreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {


    @Mapping(target = "seats", ignore = true)
    @Mapping(target = "removeSeats", ignore = true)
    Area toEntity(AreaDTO areaDTO);

    default Area fromId(Long id) {
        if (id == null) {
            return null;
        }
        Area area = new Area();
        area.setId(id);
        return area;
    }
}
