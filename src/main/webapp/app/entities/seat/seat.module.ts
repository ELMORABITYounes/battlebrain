import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BattlebrainSharedModule } from 'app/shared/shared.module';
import { SeatComponent } from './seat.component';
import { SeatDetailComponent } from './seat-detail.component';
import { SeatUpdateComponent } from './seat-update.component';
import { SeatDeleteDialogComponent } from './seat-delete-dialog.component';
import { seatRoute } from './seat.route';

@NgModule({
  imports: [BattlebrainSharedModule, RouterModule.forChild(seatRoute)],
  declarations: [SeatComponent, SeatDetailComponent, SeatUpdateComponent, SeatDeleteDialogComponent],
  entryComponents: [SeatDeleteDialogComponent],
})
export class BattlebrainSeatModule {}
