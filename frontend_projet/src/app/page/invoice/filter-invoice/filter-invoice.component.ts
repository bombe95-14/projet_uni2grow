
import { AddressService } from 'src/service/AddressServiceService';
import { CustomerService } from './../../../../service/customer-service.service';
import { InvoiceService } from './../../../../service/invoice-service.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-filter-invoice',
  templateUrl: './filter-invoice.component.html',
  styleUrls: ['./filter-invoice.component.css']
})
export class FilterInvoiceComponent implements OnInit {


      invoiceFilterFrom : FormGroup = Object.create(null);
      customers : any[] = [];
      addresses : any[] = [];

      @Input() visible : boolean = false;
      @Output() visibleChange = new EventEmitter<boolean>();

      @Output() infoFormApiServer = new EventEmitter<any>();

  constructor( private invoiceService : InvoiceService, private customerService : CustomerService, private addressService : AddressService ) { }

  ngOnInit(): void {

    this.getAddresses();
    this.getCustomers();

      this.invoiceFilterFrom = new FormGroup({
        invoiceNumber : new FormControl( null ),
        clientName : new FormControl( null),
        startDate : new FormControl( null  ),
        endDate : new FormControl( null )
      })

  }


  sendDataToServer(  ){

    this.invoiceService.filterList( this.invoiceFilterFrom.value ).subscribe({
      next: (value) => {
        this.infoFormApiServer.emit(
          {
            data : value,
            message : "success"
          }
         )
             }, error(err) {
          console.log(err);
      },
    })
}

  closeDialog(){
      this.visible = false
      this.visibleChange.emit( this.visible );
  }

  getCustomers(){
    this.customerService.getAllElement().subscribe( {

      next : (value)=> {
          this.customers = value;
      },

    } )
  }

  getAddresses(){
    this.addressService.getAllElement().subscribe( {

      next : (value) => {
        this.addresses = value
      }, error(err) {
          console.log(err);

      },

    } )
  }

}
