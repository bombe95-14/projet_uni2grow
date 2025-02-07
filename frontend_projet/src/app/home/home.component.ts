import { Component, OnInit } from '@angular/core';


import { MenuItem, MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [MessageService]


})
export class HomeComponent implements OnInit {

  items: MenuItem[] | undefined;
  itemActivatedCurrent : string = 'ADDRESS';
  constructor(private router: Router,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.items = [
      {
          label: 'Navigate',
          items: [
              {
                  label: 'ADDRESS',
                  icon: 'pi pi-palette',
                  command: () => {
                    this.itemActivatedCurrent = 'ADDRESS';
                    this.router.navigate(['/installation']);
                  }
              },
              {
                  label: 'CUSTOMER',
                  icon: 'pi pi-link',
                  command: () => {
                    this.itemActivatedCurrent = 'CUSTOMER';
                      this.router.navigate(['/installation']);
                  }
              },
              {
                  label: 'INVOICE',
                  icon: 'pi pi-home',
                  command: () => {
                    this.itemActivatedCurrent = 'INVOICE';
                    this.router.navigate(['/installation']);
                 }
              }
          ]
      }
  ];

  /* this.items = [
    {
        label: 'Address',
        icon: 'pi pi-plus',
        command: () => {
            this.update();
        }
    },
    {
        label: 'Customer',
        icon: 'pi pi-search',
        command: () => {
            this.delete();
        }
    },
    {
      label: 'Invoice',
      icon: 'pi pi-search',
      command: () => {
          this.delete();
      }
  }
]; */

}

update() {
  this.messageService.add({ severity: 'success', summary: 'Success', detail: 'File created', life: 3000 });
}

delete() {
  this.messageService.add({ severity: 'warn', summary: 'Search Completed', detail: 'No results found', life: 3000 });
}

}
