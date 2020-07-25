package com.elmorabit.battlebrain.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.elmorabit.battlebrain.web.rest.TestUtil;

public class SeatDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SeatDTO.class);
        SeatDTO seatDTO1 = new SeatDTO();
        seatDTO1.setId(1L);
        SeatDTO seatDTO2 = new SeatDTO();
        assertThat(seatDTO1).isNotEqualTo(seatDTO2);
        seatDTO2.setId(seatDTO1.getId());
        assertThat(seatDTO1).isEqualTo(seatDTO2);
        seatDTO2.setId(2L);
        assertThat(seatDTO1).isNotEqualTo(seatDTO2);
        seatDTO1.setId(null);
        assertThat(seatDTO1).isNotEqualTo(seatDTO2);
    }
}
