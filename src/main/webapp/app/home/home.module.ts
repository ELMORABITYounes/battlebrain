import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BattlebrainSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTES } from './home.route';
import { HomeComponent } from './home.component';
import { BookingComponent } from './booking/booking.component';
import { BookingAvailableSeatsComponent } from './booking/booking-available-seats.component';

@NgModule({
  imports: [BattlebrainSharedModule, RouterModule.forChild(HOME_ROUTES)],
  declarations: [HomeComponent, BookingComponent, BookingAvailableSeatsComponent],
})
export class BattlebrainHomeModule {}
