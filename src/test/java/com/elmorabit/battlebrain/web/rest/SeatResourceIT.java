package com.elmorabit.battlebrain.web.rest;

import com.elmorabit.battlebrain.BattlebrainApp;
import com.elmorabit.battlebrain.domain.Seat;
import com.elmorabit.battlebrain.repository.SeatRepository;
import com.elmorabit.battlebrain.service.SeatService;
import com.elmorabit.battlebrain.service.dto.SeatDTO;
import com.elmorabit.battlebrain.service.mapper.SeatMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.elmorabit.battlebrain.domain.enumeration.SeatStatus;
/**
 * Integration tests for the {@link SeatResource} REST controller.
 */
@SpringBootTest(classes = BattlebrainApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SeatResourceIT {

    private static final Long DEFAULT_NUMBER = 1L;
    private static final Long UPDATED_NUMBER = 2L;

    private static final SeatStatus DEFAULT_STATUS = SeatStatus.BOOKED;
    private static final SeatStatus UPDATED_STATUS = SeatStatus.AVAILABLE;

    private static final Boolean DEFAULT_AISLE_SEAT = false;
    private static final Boolean UPDATED_AISLE_SEAT = true;

    private static final Boolean DEFAULT_WINDOW_SEAT = false;
    private static final Boolean UPDATED_WINDOW_SEAT = true;

    private static final Boolean DEFAULT_MIDDLE_SEAT = false;
    private static final Boolean UPDATED_MIDDLE_SEAT = true;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private SeatService seatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSeatMockMvc;

    private Seat seat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Seat createEntity(EntityManager em) {
        Seat seat = new Seat()
            .number(DEFAULT_NUMBER)
            .status(DEFAULT_STATUS)
            .aisleSeat(DEFAULT_AISLE_SEAT)
            .windowSeat(DEFAULT_WINDOW_SEAT)
            .middleSeat(DEFAULT_MIDDLE_SEAT);
        return seat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Seat createUpdatedEntity(EntityManager em) {
        Seat seat = new Seat()
            .number(UPDATED_NUMBER)
            .status(UPDATED_STATUS)
            .aisleSeat(UPDATED_AISLE_SEAT)
            .windowSeat(UPDATED_WINDOW_SEAT)
            .middleSeat(UPDATED_MIDDLE_SEAT);
        return seat;
    }

    @BeforeEach
    public void initTest() {
        seat = createEntity(em);
    }

    @Test
    @Transactional
    public void createSeat() throws Exception {
        int databaseSizeBeforeCreate = seatRepository.findAll().size();
        // Create the Seat
        SeatDTO seatDTO = seatMapper.toDto(seat);
        restSeatMockMvc.perform(post("/api/seats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seatDTO)))
            .andExpect(status().isCreated());

        // Validate the Seat in the database
        List<Seat> seatList = seatRepository.findAll();
        assertThat(seatList).hasSize(databaseSizeBeforeCreate + 1);
        Seat testSeat = seatList.get(seatList.size() - 1);
        assertThat(testSeat.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testSeat.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSeat.isAisleSeat()).isEqualTo(DEFAULT_AISLE_SEAT);
        assertThat(testSeat.isWindowSeat()).isEqualTo(DEFAULT_WINDOW_SEAT);
        assertThat(testSeat.isMiddleSeat()).isEqualTo(DEFAULT_MIDDLE_SEAT);
    }

    @Test
    @Transactional
    public void createSeatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = seatRepository.findAll().size();

        // Create the Seat with an existing ID
        seat.setId(1L);
        SeatDTO seatDTO = seatMapper.toDto(seat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSeatMockMvc.perform(post("/api/seats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Seat in the database
        List<Seat> seatList = seatRepository.findAll();
        assertThat(seatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = seatRepository.findAll().size();
        // set the field null
        seat.setNumber(null);

        // Create the Seat, which fails.
        SeatDTO seatDTO = seatMapper.toDto(seat);


        restSeatMockMvc.perform(post("/api/seats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seatDTO)))
            .andExpect(status().isBadRequest());

        List<Seat> seatList = seatRepository.findAll();
        assertThat(seatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSeats() throws Exception {
        // Initialize the database
        seatRepository.saveAndFlush(seat);

        // Get all the seatList
        restSeatMockMvc.perform(get("/api/seats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(seat.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].aisleSeat").value(hasItem(DEFAULT_AISLE_SEAT.booleanValue())))
            .andExpect(jsonPath("$.[*].windowSeat").value(hasItem(DEFAULT_WINDOW_SEAT.booleanValue())))
            .andExpect(jsonPath("$.[*].middleSeat").value(hasItem(DEFAULT_MIDDLE_SEAT.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSeat() throws Exception {
        // Initialize the database
        seatRepository.saveAndFlush(seat);

        // Get the seat
        restSeatMockMvc.perform(get("/api/seats/{id}", seat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(seat.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.aisleSeat").value(DEFAULT_AISLE_SEAT.booleanValue()))
            .andExpect(jsonPath("$.windowSeat").value(DEFAULT_WINDOW_SEAT.booleanValue()))
            .andExpect(jsonPath("$.middleSeat").value(DEFAULT_MIDDLE_SEAT.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSeat() throws Exception {
        // Get the seat
        restSeatMockMvc.perform(get("/api/seats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSeat() throws Exception {
        // Initialize the database
        seatRepository.saveAndFlush(seat);

        int databaseSizeBeforeUpdate = seatRepository.findAll().size();

        // Update the seat
        Seat updatedSeat = seatRepository.findById(seat.getId()).get();
        // Disconnect from session so that the updates on updatedSeat are not directly saved in db
        em.detach(updatedSeat);
        updatedSeat
            .number(UPDATED_NUMBER)
            .status(UPDATED_STATUS)
            .aisleSeat(UPDATED_AISLE_SEAT)
            .windowSeat(UPDATED_WINDOW_SEAT)
            .middleSeat(UPDATED_MIDDLE_SEAT);
        SeatDTO seatDTO = seatMapper.toDto(updatedSeat);

        restSeatMockMvc.perform(put("/api/seats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seatDTO)))
            .andExpect(status().isOk());

        // Validate the Seat in the database
        List<Seat> seatList = seatRepository.findAll();
        assertThat(seatList).hasSize(databaseSizeBeforeUpdate);
        Seat testSeat = seatList.get(seatList.size() - 1);
        assertThat(testSeat.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testSeat.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSeat.isAisleSeat()).isEqualTo(UPDATED_AISLE_SEAT);
        assertThat(testSeat.isWindowSeat()).isEqualTo(UPDATED_WINDOW_SEAT);
        assertThat(testSeat.isMiddleSeat()).isEqualTo(UPDATED_MIDDLE_SEAT);
    }

    @Test
    @Transactional
    public void updateNonExistingSeat() throws Exception {
        int databaseSizeBeforeUpdate = seatRepository.findAll().size();

        // Create the Seat
        SeatDTO seatDTO = seatMapper.toDto(seat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSeatMockMvc.perform(put("/api/seats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(seatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Seat in the database
        List<Seat> seatList = seatRepository.findAll();
        assertThat(seatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSeat() throws Exception {
        // Initialize the database
        seatRepository.saveAndFlush(seat);

        int databaseSizeBeforeDelete = seatRepository.findAll().size();

        // Delete the seat
        restSeatMockMvc.perform(delete("/api/seats/{id}", seat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Seat> seatList = seatRepository.findAll();
        assertThat(seatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
