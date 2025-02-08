import { CustomerService } from './../../../service/customer-service.service';
import { AddressService } from './../../../service/AddressServiceService';
import { InvoiceService } from './../../../service/invoice-service.service';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css'],
  providers: [ConfirmationService, MessageService]

})
export class InvoiceComponent implements OnInit{

  invoices : any[] = []
  selectedElement: any[]= [];
  keyListElement!: string[];
  titleFormAddAndUpdate : string = ""

  displayDialogCreateAndUpdate : boolean = false;
  displayDialogFilter : boolean = false;
  displayDialogDelete : boolean = false;
  displayPrintInvoice : boolean = false;

  enaledUpdateButton : boolean = false;
  enabledDeleteButton : boolean = false;

  constructor( private invoiceService : InvoiceService, private confirmationService : ConfirmationService, private messageService : MessageService ){}

  ngOnInit(): void {
    this.getInvoices();
    this.selectedElement = []
    this.enabledDeleteButton = false;
    this.enaledUpdateButton = false;
  }


  displayFormFilter(){
      this.displayDialogFilter = true
  }

  onRowSelect(event: any) {
    this.selectedElement = [event.data]
    console.log(this.selectedElement);
    this.enabledDeleteButton = true;
    this.enaledUpdateButton = true;

  }

  onRowUnselect(event: any) {
    this.selectedElement = [];
    console.log(this.selectedElement);
    this.enabledDeleteButton = false;
    this.enaledUpdateButton = false;

  }

  deleteOneInvoice(){

    this.confirmationService.confirm({
      message: 'Are you sure that you want to delete?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      acceptIcon:"none",
      rejectIcon:"none",
      rejectButtonStyleClass:"p-button-text",
      accept: () => {
        this.invoiceService.deleteOneElement( this.selectedElement[0].id ).subscribe({
          next: (value) => {
            this.getInvoices();
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });

          }, error(err) {

          },
        })
      },
      reject: () => {
      }
  });

  }

  addOrUpdateOneInvoice( title : string ){
      this.displayDialogCreateAndUpdate = true;
      if ( title==="add" ) {
        this.titleFormAddAndUpdate="Create an invoice"
      } else {
        this.titleFormAddAndUpdate = "Update an invoice"
      }
  }

  getInvoices(){
    this.invoiceService.getAllElement().subscribe( {
      next : (value) => {
        this.invoices = value

        if (this.invoices.length > 0) {
          this.keyListElement = Object.keys(this.invoices[0]);
        } else {
          this.keyListElement = [];
        }
      }
   } )
  }

  resultOperationsFilter( event : any ){
    this.invoices = event.data.body;
    console.log('filter\n');
    console.log(this.invoices);
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
