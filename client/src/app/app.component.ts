import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ProvinciaService} from './state/provincia';
import {ProvinciaQuery} from './state/provincia';
import {LocalidadService} from './state/localidad';
import {Observable} from 'rxjs';
import {Provincia} from './state/provincia';
import {Localidad} from './state/localidad';
import {loadData} from "./methods/loadData";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  protected readonly String = String;

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
    loadData(this.provinciaService,
      this.localidadService,
      this.dataProvincias$,
      this.provincias,
      this.provinciaQuery,
      this.dataLocalidades$);
  }

  getLocalidadesData(provinciaId: string) {
    this.dataLocalidades$ = this.localidadService.getLocalidadesByProvincia(provinciaId);
    this.provinciaService.selectProvinciaNameById(+provinciaId).subscribe(
      name => {
        this.selectedProvinciaName = name;
      }
    );
  }

  //métodos relativos a eventos de la plantilla

  onLocalidadRowDblClick(event: any) {
    this.localidadPopupVisible = true;
    this.selectedLocalidadId = event.data.id;
    this.selectedLocalidadName = event.data.nombre;
    this.selectedLocalidad$ = this.localidadService.getLocalidadByLocalidadIdInArrayFormat(event.data.id);
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

  onInfoPopupRowUpdating(e: any) {
    this.localidadService.updateLocalidadName(e.key.id, e.newData.nombre).subscribe(
      response => {
        loadData(this.provinciaService,
          this.localidadService,
          this.dataProvincias$,
          this.provincias,
          this.provinciaQuery,
          this.dataLocalidades$);

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
        loadData(this.provinciaService,
          this.localidadService,
          this.dataProvincias$,
          this.provincias,
          this.provinciaQuery,
          this.dataLocalidades$);

        this.cdr.detectChanges();
      },
      (error)=>{
        console.error('Error eliminar la localidad:', error);
      }
    );
  }

  onLocalidadRowInserted(e: any) {
    this.provinciaService.insertLocalidadByNameInProvincia(this.selectedProvinciaId, e.key.nombre).subscribe(
      response => {
        loadData(this.provinciaService,
          this.localidadService,
          this.dataProvincias$,
          this.provincias,
          this.provinciaQuery,
          this.dataLocalidades$);

        this.cdr.detectChanges();
      },
      error => {
        console.error('Error añadiendo nueva localidad:', error);
      }
    );
  }

  reloadDataWithDelay(provinciaId: string) {
    setTimeout(() => {
      this.getLocalidadesData(provinciaId);
    }, 100);
  }

  hidePopup() {
    this.localidadPopupVisible = false;
    this.confirmingProvinciaChangePopupVisible = false;
    this.getLocalidadesData(String(this.selectedProvinciaId));
  }
}
