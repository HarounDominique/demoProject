import {Injectable} from "@angular/core";
import {QueryEntity} from "@datorama/akita";
import {LocalidadState, LocalidadStore} from "./localidad.store";

@Injectable({ providedIn: 'root' })
export class LocalidadQuery extends QueryEntity<LocalidadState>{
  constructor(protected override store: LocalidadStore){
    super(store);
  }
}
