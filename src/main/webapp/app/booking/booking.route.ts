import { Route } from '@angular/router';
import { BookingComponent } from './booking.component';

export const HOME_ROUTE: Route = {
  path: '',
  component: BookingComponent,
  data: {
    authorities: [],
    pageTitle: 'Book your seat!',
  },
};
