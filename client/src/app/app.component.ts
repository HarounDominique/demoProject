import { Component } from '@angular/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'client';
  protected readonly data = [
    {id:1, name:"John", age:30},
    {id:2, name:"Jack", age:20},
    {id:3, name:"Anne", age:40}
  ];
}
