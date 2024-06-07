import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {DevExtremeModule, DxDataGridModule, DxBoxModule } from "devextreme-angular";
import {DataService} from "./data/data.service";
import { ProvinciaService } from './state/provincia/provincia.service';
import { LocalidadService } from './state/localidad/localidad.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    DxBoxModule,
    BrowserModule,
    AppRoutingModule,
    DxDataGridModule,
    DevExtremeModule,
    HttpClientModule
  ],
  providers: [DataService, ProvinciaService, LocalidadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
