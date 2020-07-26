package com.elmorabit.battlebrain.service.impl;

import com.elmorabit.battlebrain.service.ReservationService;
import com.elmorabit.battlebrain.domain.Reservation;
import com.elmorabit.battlebrain.repository.ReservationRepository;
import com.elmorabit.battlebrain.service.UserService;
import com.elmorabit.battlebrain.service.dto.ReservationDTO;
import com.elmorabit.battlebrain.service.mapper.ReservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Reservation}.
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    private final UserService userService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper, final UserService userService) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.userService = userService;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        log.debug("Request to save Reservation : {}", reservationDTO);
        if (reservationDTO.getCollaboratorId() == null) {
            userService.getUserWithAuthorities().ifPresent(user -> reservationDTO.setCollaboratorId(user.getId()));
        }
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDTO> findAll() {
        log.debug("Request to get all Reservations");
        return reservationRepository.findByCollaboratorIsCurrentUser().stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReservationDTO> findOne(Long id) {
        log.debug("Request to get Reservation : {}", id);
        return reservationRepository.findById(id)
            .map(reservationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reservation : {}", id);
        reservationRepository.deleteById(id);
    }
}
