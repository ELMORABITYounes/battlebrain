package com.elmorabit.battlebrain.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.elmorabit.battlebrain.web.rest.TestUtil;

public class ReservationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReservationDTO.class);
        ReservationDTO reservationDTO1 = new ReservationDTO();
        reservationDTO1.setId(1L);
        ReservationDTO reservationDTO2 = new ReservationDTO();
        assertThat(reservationDTO1).isNotEqualTo(reservationDTO2);
        reservationDTO2.setId(reservationDTO1.getId());
        assertThat(reservationDTO1).isEqualTo(reservationDTO2);
        reservationDTO2.setId(2L);
        assertThat(reservationDTO1).isNotEqualTo(reservationDTO2);
        reservationDTO1.setId(null);
        assertThat(reservationDTO1).isNotEqualTo(reservationDTO2);
    }
}
