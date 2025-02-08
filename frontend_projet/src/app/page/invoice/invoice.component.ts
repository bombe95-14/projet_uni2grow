import { CustomerService } from './../../../service/customer-service.service';
import { AddressService } from './../../../service/AddressServiceService';
import { InvoiceService } from './../../../service/invoice-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
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

  constructor( private invoiceService : InvoiceService){}

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

  }

  addOrUpdateOneInvoice( title : string ){
      this.displayDialogCreateAndUpdate = true;
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
