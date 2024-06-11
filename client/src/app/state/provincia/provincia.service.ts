import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ProvinciaStore} from "./provincia.store";
import {map, Observable} from 'rxjs';
import { tap } from 'rxjs/operators';
import {Provincia} from "./provincia.model";


@Injectable({ providedIn: 'root' })
export class ProvinciaService{
  constructor(private provinciaStore: ProvinciaStore, private http: HttpClient) { }

  updateProvinciaName(newName:string){
    this.provinciaStore.update({name:newName});
  }

  loadProvincias(): Observable<Provincia[]> {
    return this.http.get<Provincia[]>('http://localhost:8080/provincias').pipe(
      tap(provincias => {
        this.provinciaStore.set(provincias);
      })
    );
  }

  getProvinciaById(provinciaId: number | null): Observable<Provincia> {
    return this.http.get<Provincia>(`http://localhost:8080/provincias/${provinciaId}`);
  }

  selectProvinciaNameById(provinciaId: number | null): Observable<string> {
    return this.http.get<Provincia>('http://localhost:8080/provincias/' + provinciaId).pipe(
      map(provincia => provincia.nombre)
    );
  }
}
