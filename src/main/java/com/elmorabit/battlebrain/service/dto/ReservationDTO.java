package com.elmorabit.battlebrain.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.elmorabit.battlebrain.domain.Reservation} entity.
 */
public class ReservationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;


    private Long collaboratorId;

    private String collaboratorLogin;

    private Long seatId;

    private String seatNumber;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Long getCollaboratorId() {
        return collaboratorId;
    }

    public void setCollaboratorId(Long userId) {
        this.collaboratorId = userId;
    }

    public String getCollaboratorLogin() {
        return collaboratorLogin;
    }

    public void setCollaboratorLogin(String userLogin) {
        this.collaboratorLogin = userLogin;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationDTO)) {
            return false;
        }

        return id != null && id.equals(((ReservationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReservationDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", collaboratorId=" + getCollaboratorId() +
            ", collaboratorLogin='" + getCollaboratorLogin() + "'" +
            ", seatId=" + getSeatId() +
            ", seatNumber='" + getSeatNumber() + "'" +
            "}";
    }
}
