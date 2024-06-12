import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ProvinciaService} from './state/provincia';
import {ProvinciaQuery} from './state/provincia';
import {LocalidadService} from './state/localidad';
import {LocalidadQuery} from './state/localidad';
import {catchError, map, Observable, of, pipe} from 'rxjs';
import {Provincia} from './state/provincia';
import {Localidad} from './state/localidad';
import DevExpress from "devextreme";
import RowUpdatingEvent = DevExpress.ui.dxDataGrid.RowUpdatingEvent;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  //OBSERVABLES:

  dataProvincias$: Observable<Provincia[]> | undefined;

  dataLocalidades$: Observable<Localidad[]> | undefined;

  selectedLocalidad$: Observable<Localidad[]> | undefined;

  //VALORES QUE SE MOSTRARÁN EN LA PLANTILLA:

  provincias: Array<Provincia> = [];

  selectedProvinciaId: number | null = null;

  selectedProvinciaName: string | null = null;

  selectedLocalidadName: string | null = null;

  selectedLocalidadId: number | null = null;

  //VARIABLES TEMPORALES (INTERMEDIAS):

  migratingLocalidad: Localidad | null = null;

  migrationTargetProvincia: Provincia | null = null;

  //VARIABLES RELATIVOS A LA LÓGICA:

  localidadPopupVisible: boolean = false;

  confirmingProvinciaChangePopupVisible: boolean = false;

  title = 'client';


  constructor(
    private provinciaService: ProvinciaService,
    private provinciaQuery: ProvinciaQuery,
    private localidadService: LocalidadService,
    private cdr: ChangeDetectorRef
  ) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.provinciaService.loadProvincias().subscribe();
    this.localidadService.loadLocalidades().subscribe();
    this.dataProvincias$ = this.provinciaQuery.selectAll();
    this.dataProvincias$.subscribe(
      provincias => {
        this.provincias = provincias;
      },
      error => {
        console.error('Error loading provincias:', error);
      }
    );
    this.dataLocalidades$ = of([]);
  }

  onLocalidadRowDblClick(event: any) {
    this.localidadPopupVisible = true;
    this.selectedLocalidadId = event.data.id;
    this.selectedLocalidadName = event.data.nombre;
    this.selectedLocalidad$ = this.localidadService.getLocalidadByLocalidadIdInArrayFormat(event.data.id);
  }

  hidePopup() {
    this.localidadPopupVisible = false;
    this.confirmingProvinciaChangePopupVisible = false;
    this.getLocalidadesData(String(this.selectedProvinciaId));
  }

  onProvinciaSelected(e: any): void {
    this.getLocalidadesData(e.value);
    this.selectedProvinciaId = e.value;
  }

  onSavingLocalidad(e: any) {
    this.localidadService.getLocalidadByLocalidadId(this.selectedLocalidadId).subscribe(
      localidad => {
        this.migratingLocalidad = localidad
      }
    );
  }

  onLocalidadProvinciaChanged(e: any) {
    this.provinciaService.getProvinciaById(e.value).subscribe(
      provincia => {
        this.migrationTargetProvincia = provincia
      }
    );
    //EL TIMEOUT DA MARGEN PARA QUE LA VARAIBLE SE ACUMULE EL VALOR, PERO NO ES UNA BUENA SOLUCIÓN
    setTimeout(() => {
      this.confirmingProvinciaChangePopupVisible = true;
    }, 100);
  }

  onConfirmProvinciaChangeButton(e: any) {
    this.provinciaService.insertLocalidadInProvincia(this.migrationTargetProvincia?.id, this.migratingLocalidad).subscribe(
      (response) => {
        this.confirmingProvinciaChangePopupVisible = false;
        this.cdr.detectChanges();  // Marcar la vista como sucia y forzar una verificación de cambios -> SIN ESTO NO FUNCIONA (?)
      },
      (error) => {
        console.error('Error añadiendo localidad:', error);
      }
    );
  }

  private getLocalidadesData(provinciaId: string) {
    this.dataLocalidades$ = this.localidadService.getLocalidadesByProvincia(provinciaId);
    this.provinciaService.selectProvinciaNameById(+provinciaId).subscribe(
      name => {
        this.selectedProvinciaName = name;
      }
    );
  }

  onInfoPopupRowUpdating(e: any) {
    this.localidadService.updateLocalidadName(e.key.id, e.newData.nombre).subscribe(
      response => {
        this.loadData();
        this.cdr.detectChanges();
      },
      (error) => {
        console.error('Error actualizando localidad:', error);
      }
    );
  }

  onInfoPopupRowRemoving(e: any) {
    this.localidadService.deleteLocalidadByLocalidadId(e.key.id).subscribe(
      response => {
        this.loadData();
        this.cdr.detectChanges();
      },
      (error)=>{
        console.error('Error eliminar la localidad:', error);
      }
    );
  }

  onInfoPopupRowInserted(e: any) {
    
  }
}

/*
ACCESO AL BACKEND A TRAVÉS DE 'data'; SUSTITUIDO POR AKITA:

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

/*
MÉTODOS PROPIOS DE LA VISTA DE PROVINCIAS, YA NO SE USAN:

onProvinciaClick(e: any): void {
    const rowData = e.data;
    var varCount = 0;
    if (rowData) {
      const provinciaId = rowData.id;
      this.selectedProvinciaId = provinciaId;
      this.dataLocalidades$ = this.localidadService.getLocalidadesByProvincia(provinciaId).pipe(
        map(localidades => {
          localidades.forEach(localidad => {
            varCount++;
          });
          this.selectedProvinciaLocalidadesNumber = varCount;
          return localidades;
        }),
        catchError(error => {
          console.error('Error loading localidades:', error, ".");
          return of([]);
        })
      );
    } else {
      console.error('Invalid event object:', e, ".");
    }

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

  onProvinciaRowDblClick(event: any) {
    this.provinciaPopupVisible = true;
  }

 */
