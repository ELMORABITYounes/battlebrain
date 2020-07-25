package com.elmorabit.battlebrain.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.elmorabit.battlebrain.domain.enumeration.SeatStatus;

/**
 * A Seat.
 */
@Entity
@Table(name = "seat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number", nullable = false)
    private Long number;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SeatStatus status;

    @Column(name = "aisle_seat")
    private Boolean aisleSeat;

    @Column(name = "window_seat")
    private Boolean windowSeat;

    @Column(name = "middle_seat")
    private Boolean middleSeat;

    @OneToOne
    @JoinColumn(unique = true)
    private Seat rightSeat;

    @OneToOne
    @JoinColumn(unique = true)
    private Seat leftSeat;

    @OneToOne
    @JoinColumn(unique = true)
    private Seat frontSeat;

    @OneToMany(mappedBy = "seat")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "seats", allowSetters = true)
    private Area area;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public Seat number(Long number) {
        this.number = number;
        return this;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public Seat status(SeatStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Boolean isAisleSeat() {
        return aisleSeat;
    }

    public Seat aisleSeat(Boolean aisleSeat) {
        this.aisleSeat = aisleSeat;
        return this;
    }

    public void setAisleSeat(Boolean aisleSeat) {
        this.aisleSeat = aisleSeat;
    }

    public Boolean isWindowSeat() {
        return windowSeat;
    }

    public Seat windowSeat(Boolean windowSeat) {
        this.windowSeat = windowSeat;
        return this;
    }

    public void setWindowSeat(Boolean windowSeat) {
        this.windowSeat = windowSeat;
    }

    public Boolean isMiddleSeat() {
        return middleSeat;
    }

    public Seat middleSeat(Boolean middleSeat) {
        this.middleSeat = middleSeat;
        return this;
    }

    public void setMiddleSeat(Boolean middleSeat) {
        this.middleSeat = middleSeat;
    }

    public Seat getRightSeat() {
        return rightSeat;
    }

    public Seat rightSeat(Seat seat) {
        this.rightSeat = seat;
        return this;
    }

    public void setRightSeat(Seat seat) {
        this.rightSeat = seat;
    }

    public Seat getLeftSeat() {
        return leftSeat;
    }

    public Seat leftSeat(Seat seat) {
        this.leftSeat = seat;
        return this;
    }

    public void setLeftSeat(Seat seat) {
        this.leftSeat = seat;
    }

    public Seat getFrontSeat() {
        return frontSeat;
    }

    public Seat frontSeat(Seat seat) {
        this.frontSeat = seat;
        return this;
    }

    public void setFrontSeat(Seat seat) {
        this.frontSeat = seat;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Seat reservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public Seat addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setSeat(this);
        return this;
    }

    public Seat removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setSeat(null);
        return this;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Area getArea() {
        return area;
    }

    public Seat area(Area area) {
        this.area = area;
        return this;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Seat)) {
            return false;
        }
        return id != null && id.equals(((Seat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Seat{" +
            "id=" + getId() +
            ", number=" + getNumber() +
            ", status='" + getStatus() + "'" +
            ", aisleSeat='" + isAisleSeat() + "'" +
            ", windowSeat='" + isWindowSeat() + "'" +
            ", middleSeat='" + isMiddleSeat() + "'" +
            "}";
    }
}
