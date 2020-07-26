import { Component, OnInit } from '@angular/core';
import { ISeat } from '../../shared/model/seat.model';
import { BookingService } from './booking.service';
import { Router } from '@angular/router';
import { ReservationService } from '../../entities/reservation/reservation.service';
import { IReservation, Reservation } from '../../shared/model/reservation.model';
import { AccountService } from '../../core/auth/account.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-available-seats',
  templateUrl: './booking-available-seats.component.html',
})
export class BookingAvailableSeatsComponent implements OnInit {
  seats?: ISeat[] = [];
  predicate!: string;
  name = 'younes';
  ascending!: boolean;

  constructor(
    private bookingService: BookingService,
    private reservationService: ReservationService,
    private accountService: AccountService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.seats = this.bookingService.availableSeats;
  }

  trackId(index: number, item: ISeat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  bookSeat(seat: ISeat): void {
    const reservation1 = new Reservation();
    reservation1.seatId = seat.id;
    reservation1.startDate = this.bookingService.bookingForm.startDate;
    reservation1.endDate = this.bookingService.bookingForm.endDate;
    this.subscribeToSaveResponse(this.reservationService.create(reservation1));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReservation>>): void {
    result.subscribe(value => this.onSaveSuccess(value));
  }

  protected onSaveSuccess(value: HttpResponse<IReservation>): void {
    if (value.body) this.router.navigate(['/reservation', value.body.id, 'view']);
  }
}
