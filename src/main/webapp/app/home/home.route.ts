import { Route } from '@angular/router';

import { HomeComponent } from './home.component';
import { BookingComponent } from './booking/booking.component';

export const HOME_ROUTES: Route[] = [
  {
    path: '',
    component: HomeComponent,
    data: {
      authorities: [],
      pageTitle: 'Welcome, Java Hipster!',
    },
  },
  {
    path: 'booking',
    component: BookingComponent,
    data: {
      authorities: [],
      pageTitle: 'Seat booking',
    },
  },
];
