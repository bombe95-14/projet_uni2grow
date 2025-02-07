import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-global-table',
  templateUrl: './global-table.component.html',
  styleUrls: ['./global-table.component.css']
})
export class GlobalTableComponent implements OnInit {


  @Input() elements: any[] = [];
  selectedElement!: any;
  keyListElement!: string[];


  constructor() { }

  ngOnInit(): void {

    if (this.elements.length > 0) {
      this.keyListElement = Object.keys(this.elements[0]);
      console.log('====================================');
      console.log(this.keyListElement);
      console.log('====================================');
    } else {
      this.keyListElement = [];
    }

  }

}
