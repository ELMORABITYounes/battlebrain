import { IReservation } from 'app/shared/model/reservation.model';
import { SeatStatus } from 'app/shared/model/enumerations/seat-status.model';

export interface ISeat {
  id?: number;
  number?: number;
  status?: SeatStatus;
  aisleSeat?: boolean;
  windowSeat?: boolean;
  middleSeat?: boolean;
  rightSeatNumber?: string;
  rightSeatId?: number;
  leftSeatNumber?: string;
  leftSeatId?: number;
  frontSeatNumber?: string;
  frontSeatId?: number;
  reservations?: IReservation[];
  areaName?: string;
  areaId?: number;
}

export class Seat implements ISeat {
  constructor(
    public id?: number,
    public number?: number,
    public status?: SeatStatus,
    public aisleSeat?: boolean,
    public windowSeat?: boolean,
    public middleSeat?: boolean,
    public rightSeatNumber?: string,
    public rightSeatId?: number,
    public leftSeatNumber?: string,
    public leftSeatId?: number,
    public frontSeatNumber?: string,
    public frontSeatId?: number,
    public reservations?: IReservation[],
    public areaName?: string,
    public areaId?: number
  ) {
    this.aisleSeat = this.aisleSeat || false;
    this.windowSeat = this.windowSeat || false;
    this.middleSeat = this.middleSeat || false;
  }
}
