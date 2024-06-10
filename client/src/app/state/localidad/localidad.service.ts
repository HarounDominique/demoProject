import {Injectable} from "@angular/core";
import {LocalidadStore} from "./localidad.store";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of} from "rxjs";
import {Localidad} from "./localidad.model";
import {tap} from "rxjs/operators";


@Injectable({ providedIn: 'root' })
export class LocalidadService{

  private apiUrl = 'http://localhost:8080';

  constructor(private localidadStore: LocalidadStore, private http: HttpClient) {}

  updateLocalidadName(newName:string){
    this.localidadStore.update({name:newName});
  }

  loadLocalidades(): Observable<Localidad[]> {
    return this.http.get<Localidad[]>(`${this.apiUrl}/localidades`).pipe(
      tap(localidades => {
        this.localidadStore.set(localidades);
      })
    );
  }

  getLocalidadesByProvincia(provinciaId: string): Observable<Localidad[]> {
    return this.http.get<Localidad[]>(`${this.apiUrl}/provincias/${provinciaId}/localidades`).pipe(
      tap(localidades => {
        this.localidadStore.set(localidades);
      }),
      catchError(error => {
        console.error('Error fetching localidades:', error);
        return of([]);
      })
    );
  }
}
