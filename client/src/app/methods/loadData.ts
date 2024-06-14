import {Observable, of} from "rxjs";
import {Provincia, ProvinciaQuery, ProvinciaService} from "../state/provincia";
import {Localidad, LocalidadService} from "../state/localidad";


export function loadData(provinciaService: ProvinciaService,
                         localidadService: LocalidadService,
                         dataProvincias$: Observable<Provincia[]> | undefined,
                         provincias: Provincia[],
                         provinciaQuery: ProvinciaQuery,
                         dataLocalidades$: Observable<Localidad[]> | undefined)  {
  provinciaService.loadProvincias().subscribe();
  localidadService.loadLocalidades().subscribe();
  dataProvincias$ = provinciaQuery.selectAll();
  dataProvincias$.subscribe(
    response => {
      provincias.splice(0, provincias.length, ...response); //parece ser que sin el uso de esta función splice, sólo se modifica la referencia a la variable y no la variable en sí
    },
    error => {
      console.error('Error loading provincias:', error);
    }
  );
  dataLocalidades$ = of([]);
}
