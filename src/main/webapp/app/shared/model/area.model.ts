import { ISeat } from 'app/shared/model/seat.model';

export interface IArea {
  id?: number;
  name?: string;
  seats?: ISeat[];
}

export class Area implements IArea {
  constructor(public id?: number, public name?: string, public seats?: ISeat[]) {}
}
