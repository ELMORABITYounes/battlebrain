import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../app.constants';
import { HttpClient } from '@angular/common/http';
import { ISeat } from '../../shared/model/seat.model';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  availableSeats?: ISeat[] = [];
  bookingForm: any;

  constructor(private http: HttpClient) {}

  getAvailableSeats(bookingForm: any): Observable<ISeat[]> {
    return this.http.post<ISeat[]>(SERVER_API_URL + 'api/booking', bookingForm);
  }
}
