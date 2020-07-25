import { Moment } from 'moment';

export interface IReservation {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  collaboratorLogin?: string;
  collaboratorId?: number;
  seatNumber?: string;
  seatId?: number;
}

export class Reservation implements IReservation {
  constructor(
    public id?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public collaboratorLogin?: string,
    public collaboratorId?: number,
    public seatNumber?: string,
    public seatId?: number
  ) {}
}
