package com.elmorabit.battlebrain.service.mapper;


import com.elmorabit.battlebrain.domain.*;
import com.elmorabit.battlebrain.service.dto.TeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {


    @Mapping(target = "members", ignore = true)
    @Mapping(target = "removeMembers", ignore = true)
    Team toEntity(TeamDTO teamDTO);

    default Team fromId(Long id) {
        if (id == null) {
            return null;
        }
        Team team = new Team();
        team.setId(id);
        return team;
    }
}
