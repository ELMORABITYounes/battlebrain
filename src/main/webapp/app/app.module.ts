import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { BattlebrainSharedModule } from 'app/shared/shared.module';
import { BattlebrainCoreModule } from 'app/core/core.module';
import { BattlebrainAppRoutingModule } from './app-routing.module';
import { BattlebrainHomeModule } from './home/home.module';
import { BattlebrainEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    BattlebrainSharedModule,
    BattlebrainCoreModule,
    BattlebrainHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    BattlebrainEntityModule,
    BattlebrainAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class BattlebrainAppModule {}
