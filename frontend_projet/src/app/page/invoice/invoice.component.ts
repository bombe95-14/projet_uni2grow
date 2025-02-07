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

  constructor( private invoiceService : InvoiceService){}

  ngOnInit(): void {
    this.getInvoices();
    this.selectedElement = []
  }


  displayFormFilter(){

  }

  onRowSelect(event: any) {
    this.selectedElement = [event.data]
    console.log(this.selectedElement);

  }

  onRowUnselect(event: any) {
    this.selectedElement = [];
    console.log(this.selectedElement);

  }

  deleteOneInvoice(){

  }

  addOrUpdateOneInvoice( title : string ){

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
}
