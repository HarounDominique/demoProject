import {Localidad} from "../localidad/localidad.model";

export interface Provincia {
  id:number;
  name: string;
  localidades?: Localidad[];
}
