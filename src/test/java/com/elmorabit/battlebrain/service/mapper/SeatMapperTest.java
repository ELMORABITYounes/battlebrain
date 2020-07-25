package com.elmorabit.battlebrain.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SeatMapperTest {

    private SeatMapper seatMapper;

    @BeforeEach
    public void setUp() {
        seatMapper = new SeatMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(seatMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(seatMapper.fromId(null)).isNull();
    }
}
