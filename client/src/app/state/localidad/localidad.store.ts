import {EntityState, EntityStore, StoreConfig} from "@datorama/akita";
import {Localidad} from "./localidad.model";
import {Injectable} from "@angular/core";

export interface LocalidadState extends EntityState<Localidad>{}

export function createInitialState(): LocalidadState {
  return{}
}

@Injectable({ providedIn: 'root' })
@StoreConfig({name:'localidad'})
export class LocalidadStore extends EntityStore<LocalidadState>{
  constructor(){
    super(createInitialState());
  }
}
