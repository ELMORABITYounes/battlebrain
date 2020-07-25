package com.elmorabit.battlebrain.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.elmorabit.battlebrain.domain.enumeration.SeatStatus;

/**
 * A DTO for the {@link com.elmorabit.battlebrain.domain.Seat} entity.
 */
public class SeatDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long number;

    private SeatStatus status;

    private Boolean aisleSeat;

    private Boolean windowSeat;

    private Boolean middleSeat;


    private Long rightSeatId;

    private String rightSeatNumber;

    private Long leftSeatId;

    private String leftSeatNumber;

    private Long frontSeatId;

    private String frontSeatNumber;

    private Long areaId;

    private String areaName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Boolean isAisleSeat() {
        return aisleSeat;
    }

    public void setAisleSeat(Boolean aisleSeat) {
        this.aisleSeat = aisleSeat;
    }

    public Boolean isWindowSeat() {
        return windowSeat;
    }

    public void setWindowSeat(Boolean windowSeat) {
        this.windowSeat = windowSeat;
    }

    public Boolean isMiddleSeat() {
        return middleSeat;
    }

    public void setMiddleSeat(Boolean middleSeat) {
        this.middleSeat = middleSeat;
    }

    public Long getRightSeatId() {
        return rightSeatId;
    }

    public void setRightSeatId(Long seatId) {
        this.rightSeatId = seatId;
    }

    public String getRightSeatNumber() {
        return rightSeatNumber;
    }

    public void setRightSeatNumber(String seatNumber) {
        this.rightSeatNumber = seatNumber;
    }

    public Long getLeftSeatId() {
        return leftSeatId;
    }

    public void setLeftSeatId(Long seatId) {
        this.leftSeatId = seatId;
    }

    public String getLeftSeatNumber() {
        return leftSeatNumber;
    }

    public void setLeftSeatNumber(String seatNumber) {
        this.leftSeatNumber = seatNumber;
    }

    public Long getFrontSeatId() {
        return frontSeatId;
    }

    public void setFrontSeatId(Long seatId) {
        this.frontSeatId = seatId;
    }

    public String getFrontSeatNumber() {
        return frontSeatNumber;
    }

    public void setFrontSeatNumber(String seatNumber) {
        this.frontSeatNumber = seatNumber;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SeatDTO)) {
            return false;
        }

        return id != null && id.equals(((SeatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SeatDTO{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", status='" + getStatus() + "'" +
            ", aisleSeat='" + isAisleSeat() + "'" +
            ", windowSeat='" + isWindowSeat() + "'" +
            ", middleSeat='" + isMiddleSeat() + "'" +
            ", rightSeatId=" + getRightSeatId() +
            ", rightSeatNumber='" + getRightSeatNumber() + "'" +
            ", leftSeatId=" + getLeftSeatId() +
            ", leftSeatNumber='" + getLeftSeatNumber() + "'" +
            ", frontSeatId=" + getFrontSeatId() +
            ", frontSeatNumber='" + getFrontSeatNumber() + "'" +
            ", areaId=" + getAreaId() +
            ", areaName='" + getAreaName() + "'" +
            "}";
    }
}
