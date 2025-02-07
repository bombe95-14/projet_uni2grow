import { Component, OnInit } from '@angular/core';
import { AddressService } from 'src/service/AddressServiceService';
import { CustomerService } from 'src/service/customer-service.service';

@Component({
  selector: 'app-create-and-update-invoice',
  templateUrl: './create-and-update-invoice.component.html',
  styleUrls: ['./create-and-update-invoice.component.css']
})
export class CreateAndUpdateInvoiceComponent implements OnInit {

  constructor( private addressService : AddressService, private customerService : CustomerService  ){}

  ngOnInit(): void {

  }


}
