import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {DevExtremeModule, DxDataGridModule} from "devextreme-angular";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DxDataGridModule,
    DevExtremeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
