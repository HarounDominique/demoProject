import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ProvinciaStore} from "./provincia.store";
import {map, Observable} from 'rxjs';
import { tap } from 'rxjs/operators';
import {Provincia} from "./provincia.model";
import {Localidad} from "../localidad/localidad.model";


@Injectable({ providedIn: 'root' })
export class ProvinciaService{

  private apiUrl = 'http://localhost:8080';

  constructor(private provinciaStore: ProvinciaStore, private http: HttpClient) { }

  loadProvincias(): Observable<Provincia[]> {
    return this.http.get<Provincia[]>('http://localhost:8080/provincias').pipe(
      tap(provincias => {
        this.provinciaStore.set(provincias);
      })
    );
  }

  getProvinciaById(provinciaId: number | null): Observable<Provincia> {
    return this.http.get<Provincia>(`${this.apiUrl}/provincias/${provinciaId}`);
  }

  selectProvinciaNameById(provinciaId: number | null): Observable<string> {
    return this.http.get<Provincia>(`${this.apiUrl}/provincias/` + provinciaId).pipe(
      map(provincia => provincia.nombre)
    );
  }

  insertLocalidadInProvincia(provinciaId: number | undefined, localidad: Localidad | null): Observable<Provincia> {
    return this.http.post<Provincia>(`${this.apiUrl}/provincias/${provinciaId}`, localidad)
  }

  insertLocalidadByNameInProvincia(provinciaId: number | null, localidadName: string | null): Observable<Provincia> {
    //const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<Provincia>(`${this.apiUrl}/provincias/${provinciaId}/localidades`, localidadName);
  }

}
