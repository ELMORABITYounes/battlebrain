import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'team',
        loadChildren: () => import('./team/team.module').then(m => m.BattlebrainTeamModule),
      },
      {
        path: 'area',
        loadChildren: () => import('./area/area.module').then(m => m.BattlebrainAreaModule),
      },
      {
        path: 'seat',
        loadChildren: () => import('./seat/seat.module').then(m => m.BattlebrainSeatModule),
      },
      {
        path: 'reservation',
        loadChildren: () => import('./reservation/reservation.module').then(m => m.BattlebrainReservationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BattlebrainEntityModule {}
