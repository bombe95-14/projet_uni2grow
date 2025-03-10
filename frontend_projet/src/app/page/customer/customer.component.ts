import { CustomerService } from './../../../service/customer-service.service';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
  providers: [ConfirmationService, MessageService]

})
export class CustomerComponent implements OnInit {

  customers : any[] = [] ;
  selectedElement: any[]= [];
  keyListElement!: string[];
  titleFormAddAndUpdate : string = ""

  displayDialogCreateAndUpdate : boolean = false;
  displayDialogFilter : boolean = false;
  displayDialogDelete : boolean = false;

  enaledUpdateButton : boolean = false;
  enabledDeleteButton : boolean = false;

  constructor( private customerService : CustomerService, private confirmationService : ConfirmationService, private messageService : MessageService  ){}

  ngOnInit(): void {
     this.getCustomers();
     this.selectedElement = []
     this.displayDialogCreateAndUpdate = false
     this.displayDialogFilter = false;

     this.enabledDeleteButton = false;
     this.enaledUpdateButton = false;
  }

  onRowSelect(event: any) {
    this.selectedElement = [event.data]

     this.enabledDeleteButton = true;
     this.enaledUpdateButton = true;
  }

  onRowUnselect(event: any) {
    this.selectedElement = [];

    this.enabledDeleteButton = false;
    this.enaledUpdateButton = false;
  }

  displayFormFilter(){
    this.displayDialogFilter = true
  }

  deleteOneCustomer(){
    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      acceptIcon:"none",
      rejectIcon:"none",
      rejectButtonStyleClass:"p-button-text",
      accept: () => {
        this.customerService.deleteOneElement( this.selectedElement[0].id ).subscribe({
          next: (value) => {
            this.getCustomers();
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });

          }, error(err) {

          },
        })
      },
      reject: () => {
      }
  });
  }

  addOrUpdateOneCustomer(title : string){

    this.displayDialogCreateAndUpdate = true;

    if ( title==="add" ) {
      this.titleFormAddAndUpdate="Create an customer"
    } else {
      this.titleFormAddAndUpdate = "Update an customer"
    }

  }

  getCustomers(){
    this.customerService.getAllElement().subscribe( {

      next : (value)=> {
          this.customers = value;

          if (this.customers.length > 0) {
            this.keyListElement = Object.keys(this.customers[0]).filter( (element) => element!=="idAdress" );
          } else {
            this.keyListElement = [];
          }
      },

    } )
  }

  resultOperationsCreateAndUpdate(event:any){
    const message = event.data.body;
    this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });

      if (message === "update success") {
        this.closeAllDialogAndDisabledButton()
        this.getCustomers()

      }
  }

  resultOperationsFilter( event : any ){
    this.customers = event.data.body;
    console.log('filter\n');
    console.log(this.customers);
    console.log('filter\n');
}

closeAllDialogAndDisabledButton(){
  this.enabledDeleteButton = false;
  this.enaledUpdateButton = false;
  this.displayDialogCreateAndUpdate = false
  this.selectedElement = []
  this.displayDialogFilter = false

}
}
