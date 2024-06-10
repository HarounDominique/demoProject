import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  //EL ACCESO AL BACKEND SE GESTIONA AHORA DESDE LA IMPLEMENTACIÓN DE AKITA

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // Ejemplo de método GET -> Provincias
  getProvinciasData(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/provincias/localidades`);
  }

  // Ejemplo de método GET -> Localidades
  getLocalidadesData(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/localidades`);
  }
}
