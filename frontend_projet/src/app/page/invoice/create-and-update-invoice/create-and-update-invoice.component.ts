import { InvoiceService } from './../../../../service/invoice-service.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddressService } from 'src/service/AddressServiceService';
import { CustomerService } from 'src/service/customer-service.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-create-and-update-invoice',
  templateUrl: './create-and-update-invoice.component.html',
  styleUrls: ['./create-and-update-invoice.component.css'],
  providers: [ConfirmationService, MessageService]

})
export class CreateAndUpdateInvoiceComponent implements OnInit {


    invoiceForm : FormGroup = Object.create(null);
    invoiceItemForm : FormGroup = Object.create(null);

    addresses : any[] = [];
    customers : any[] = [];
    invoiceItemsOfTable : any[] = [];
    displayInvoiceItemDialog : boolean = false;

      @Input() titleWindow : string = "";
      @Input() invoice : any;
      @Input() visible : boolean = false;

      @Output() visibleChange = new EventEmitter<boolean>();
      @Output() infoFormApiServer = new EventEmitter<any>();

  constructor( private addressService : AddressService, private customerService : CustomerService, private invoiceService : InvoiceService ,
    private confirmationService : ConfirmationService, private messageService : MessageService
   ){}

  ngOnInit(): void {

    this.getAddresses();
    this.getCustomers();
      this.invoiceForm = new FormGroup({
        totalAmount : new FormControl( null, [ Validators.required ] ),
        idCustomer : new FormControl( null, [ Validators.required ] ),
        idAddress : new FormControl( null, [ Validators.required ] ),

      })

      this.invoiceItemForm = new FormGroup({
        name : new FormControl( null, [ Validators.required ] ),
        quantity : new FormControl( null, [ Validators.required ] ),
        price : new FormControl( null, [ Validators.required ] ),
        total : new FormControl( null, [ Validators.required ] ),
      })

  }

  closeWindow(){
    this.visible = false
    this.visibleChange.emit(false)
  }

  sendData(){

    let bodyRequest = {
      totalAmount : this.invoiceForm.get('totalAmount')?.value,
      idCustomer : this.invoiceForm.get('idCustomer')?.value,
      idAddress : this.invoiceForm.get('idAddress')?.value,
      invoiceItemDtos : this.invoiceItemsOfTable,
    }

    if ( this.titleWindow==="Create an customer" ) {
      this.invoiceService.save( bodyRequest ).subscribe( {
        next: (value) => {
                this.visible=false
                this.visibleChange.emit(this.visible)
                this.infoFormApiServer.emit(
                  {
                    data : "create success"
                  }
                 )
               this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });
        }, error : (err) => {

          this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });

        },
      } );
    }  else {
      this.invoiceService.update( {...bodyRequest, idInvoice : this.invoice.id } ).subscribe( {
        next: (value) => {
          this.visible=false
          this.visibleChange.emit(this.visible)
          this.infoFormApiServer.emit(
            {
              data : "create success"
            }
           )
           this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });

       }, error : (err) => {

          this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });

        },
      } );
    }

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

  displayDialogAddItem(){
    this.displayInvoiceItemDialog = true;
  }

  closeInvoiceItemDialog(){
    this.displayInvoiceItemDialog = false;
    this.invoiceItemForm.reset();
  }

  addInvoiceItem(){
    this.invoiceItemsOfTable.push( this.invoiceItemForm.value )
    this.closeInvoiceItemDialog();
  }
}
