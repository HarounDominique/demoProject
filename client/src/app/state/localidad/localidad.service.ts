import {Injectable} from "@angular/core";
import {LocalidadStore} from "./localidad.store";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Localidad} from "./localidad.model";
import {tap} from "rxjs/operators";


@Injectable({ providedIn: 'root' })
export class LocalidadService{
  constructor(private localidadStore: LocalidadStore, private http: HttpClient) {}

  updateLocalidadName(newName:string){
    this.localidadStore.update({name:newName});
  }

  loadLocalidades(): Observable<Localidad[]> {
    return this.http.get<Localidad[]>('http://localhost:8080/localidades').pipe(
      tap(localidades => {
        this.localidadStore.set(localidades);
      })
    );
  }
}
