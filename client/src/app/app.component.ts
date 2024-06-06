import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DataService} from "./data/data.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{

  title = 'client';

  protected dataProvincias: any;

  protected dataLocalidades: any;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.getDataProvincias();

    this.getLocalidadesData();
  }

  private getDataProvincias() {
    this.dataService.getProvinciasData().subscribe(
      (response)=>{
        this.dataProvincias=response;
      },
      (error)=>{
        console.log("Error fetching data: ",error);
      }
    )
  }

  private getLocalidadesData() {
    this.dataService.getLocalidadesData().subscribe(
      (response)=>{
        this.dataLocalidades=response;
      },
      (error)=>{
        console.log("Error fetching data: ",error);
      }
    )
  }
}
