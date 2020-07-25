import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISeat, Seat } from 'app/shared/model/seat.model';
import { SeatService } from './seat.service';
import { SeatComponent } from './seat.component';
import { SeatDetailComponent } from './seat-detail.component';
import { SeatUpdateComponent } from './seat-update.component';

@Injectable({ providedIn: 'root' })
export class SeatResolve implements Resolve<ISeat> {
  constructor(private service: SeatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISeat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((seat: HttpResponse<Seat>) => {
          if (seat.body) {
            return of(seat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Seat());
  }
}

export const seatRoute: Routes = [
  {
    path: '',
    component: SeatComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Seats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SeatDetailComponent,
    resolve: {
      seat: SeatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SeatUpdateComponent,
    resolve: {
      seat: SeatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seats',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SeatUpdateComponent,
    resolve: {
      seat: SeatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Seats',
    },
    canActivate: [UserRouteAccessService],
  },
];
