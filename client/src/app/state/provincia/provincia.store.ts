import {EntityState, EntityStore, StoreConfig} from "@datorama/akita";
import {Provincia} from "./provincia.model";
import { Injectable } from "@angular/core";

export interface ProvinciaState extends EntityState<Provincia> {}

export function createInitialState(): ProvinciaState{
  return{}
}

@Injectable({ providedIn: 'root' })
@StoreConfig({ name: 'provincia' })
export class ProvinciaStore extends EntityStore<ProvinciaState> {
  constructor() {
    super(createInitialState());
  }
}
