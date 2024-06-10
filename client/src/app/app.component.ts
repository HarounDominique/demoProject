import { Component, OnInit } from '@angular/core';
import { ProvinciaService } from './state/provincia/provincia.service';
import { ProvinciaQuery } from './state/provincia/provincia.query';
import { LocalidadService } from './state/localidad/localidad.service';
import { LocalidadQuery } from './state/localidad/localidad.query';
import {catchError, Observable, of, pipe} from 'rxjs';
import { Provincia } from './state/provincia/provincia.model';
import { Localidad } from './state/localidad/localidad.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'client';

  dataProvincias$: Observable<Provincia[]> | undefined;

  dataLocalidades$: Observable<Localidad[]> | undefined;

  dataProvincia$: Observable<string> | undefined;

  selectedProvinciaId: number | null = null;

  selectedProvinciaName: string | null = null;

  popupVisible: boolean = false;

  constructor(
    private provinciaService: ProvinciaService,
    private provinciaQuery: ProvinciaQuery,
    private localidadService: LocalidadService,
    private localidadQuery: LocalidadQuery
  ) {}

  ngOnInit(): void {
    this.loadData();
    this.dataProvincias$ = this.provinciaQuery.selectAll();
    this.dataLocalidades$ = of([]);
  }

  loadData() {
    this.provinciaService.loadProvincias().subscribe();
    this.localidadService.loadLocalidades().subscribe();
  }

  onProvinciaClick(e: any): void {
    const rowData = e.data;
    if (rowData) {
      const provinciaId = rowData.id;
      this.selectedProvinciaId = provinciaId;
      this.dataLocalidades$ = this.localidadService.getLocalidadesByProvincia(provinciaId).pipe(
        catchError(error => {
          console.error('Error loading localidades:', error, ".");
          return of([]);
        })
      );
    } else {
      console.error('Invalid event object:', e, ".");
    }
  }

  onRowDblClick(event: any) {
    this.popupVisible = true;
    if(this.selectedProvinciaId !== null) {
      this.dataProvincia$ = this.provinciaService.selectProvinciaNameById(this.selectedProvinciaId);
      this.provinciaService.selectProvinciaNameById(this.selectedProvinciaId).subscribe(
        name => {
          this.selectedProvinciaName = name;
        },
        error => {
          console.error("Error retrieving provincia name:", error);
        }
      );
    } else {
      console.error("The id of provincia is null.");
    }
  }

  hidePopup() {
    this.popupVisible = false;
  }

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
}
 */

