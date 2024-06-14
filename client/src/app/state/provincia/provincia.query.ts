import { Injectable } from '@angular/core';
import { QueryEntity } from '@datorama/akita';
import { ProvinciaStore, ProvinciaState } from './provincia.store';

@Injectable({ providedIn: 'root' })
export class ProvinciaQuery extends QueryEntity<ProvinciaState> {
  constructor(protected override store: ProvinciaStore) {
    super(store);
  }

}
