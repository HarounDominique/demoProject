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

  protected data: any;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.getData();
  }

  private getData() {
    this.dataService.getData().subscribe(
      (response)=>{
        this.data=response;
      },
      (error)=>{
        console.log("Error fetching data: ",error);
      }
    )
  }
}
