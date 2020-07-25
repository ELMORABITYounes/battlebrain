package com.elmorabit.battlebrain.service.impl;

import com.elmorabit.battlebrain.service.SeatService;
import com.elmorabit.battlebrain.domain.Seat;
import com.elmorabit.battlebrain.repository.SeatRepository;
import com.elmorabit.battlebrain.service.dto.SeatDTO;
import com.elmorabit.battlebrain.service.mapper.SeatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Seat}.
 */
@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    private final Logger log = LoggerFactory.getLogger(SeatServiceImpl.class);

    private final SeatRepository seatRepository;

    private final SeatMapper seatMapper;

    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    @Override
    public SeatDTO save(SeatDTO seatDTO) {
        log.debug("Request to save Seat : {}", seatDTO);
        Seat seat = seatMapper.toEntity(seatDTO);
        seat = seatRepository.save(seat);
        return seatMapper.toDto(seat);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SeatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Seats");
        return seatRepository.findAll(pageable)
            .map(seatMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SeatDTO> findOne(Long id) {
        log.debug("Request to get Seat : {}", id);
        return seatRepository.findById(id)
            .map(seatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Seat : {}", id);
        seatRepository.deleteById(id);
    }
}
