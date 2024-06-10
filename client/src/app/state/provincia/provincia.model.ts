import {Localidad} from "../localidad/localidad.model";

export interface Provincia {
  id:number;
  nombre: string;
  localidades?: Localidad[];
}
