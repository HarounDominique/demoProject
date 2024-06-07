import {Component, OnInit} from '@angular/core';
import {DataService} from "./data/data.service";
import { ProvinciaService } from './state/provincia/provincia.service';
import { ProvinciaQuery } from './state/provincia/provincia.query';
import { LocalidadService } from './state/localidad/localidad.service';
import { LocalidadQuery } from './state/localidad/localidad.query';
import { Observable } from 'rxjs';
import { Provincia } from './state/provincia/provincia.model';
import { Localidad } from './state/localidad/localidad.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  title = 'client';

  dataProvincias$: Observable<Provincia[]> | undefined;

  dataLocalidades$: Observable<Localidad[]> | undefined;

  //dataProvincias: any;

  //dataLocalidades: any;

  constructor(
    //private dataService: DataService,
    private provinciaService: ProvinciaService,
    private provinciaQuery: ProvinciaQuery,
    private localidadService: LocalidadService,
    private localidadQuery: LocalidadQuery
  ) {}

  ngOnInit(): void {
    //this.getDataProvincias();

    //this.getLocalidadesData();

    this.loadData();
    this.dataProvincias$ = this.provinciaQuery.selectAll();
    this.dataLocalidades$ = this.localidadQuery.selectAll();
  }

  loadData() {
    this.provinciaService.loadProvincias().subscribe();
    this.localidadService.loadLocalidades().subscribe();
  }

  onProvinciaClick(e: any): void {
    const provinciaId = e.row.data.id;
    // Aquí podrías filtrar las localidades por la provincia seleccionada
  }
/*
  private getDataProvincias() {
    this.dataService.getProvinciasData().subscribe(
      (response)=>{
        this.dataProvincias=response;
      },
      (error)=>{
        console.log("Error fetching data: ",error);
      }
    )
  }

  private getLocalidadesData() {
    this.dataService.getLocalidadesData().subscribe(
      (response)=>{
        this.dataLocalidades=response;
      },
      (error)=>{
        console.log("Error fetching data: ",error);
      }
    )
  }

 */
}
