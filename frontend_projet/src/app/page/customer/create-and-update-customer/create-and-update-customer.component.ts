import { CustomerService } from 'src/service/customer-service.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-create-and-update-customer',
  templateUrl: './create-and-update-customer.component.html',
  styleUrls: ['./create-and-update-customer.component.css'],
  providers: [ConfirmationService, MessageService]

})
export class CreateAndUpdateCustomerComponent implements OnInit{

    customerFrom : FormGroup = Object.create(null);
    @Input() titleWindow : string = "";
    @Input() customer : any;
    @Input() visible : boolean = false;

    @Output() visibleChange = new EventEmitter<boolean>();
    @Output() infoFormApiServer = new EventEmitter<any>();

    constructor( private customerService : CustomerService,
      private confirmationService : ConfirmationService, private messageService : MessageService  ){}



  ngOnInit(): void {
     this.customerFrom = new FormGroup({
             name : new FormControl( null, [ Validators.required ] ),
             email : new FormControl( null, [ Validators.required ] ),
             phone : new FormControl( null, [ Validators.required ] ),


             street : new FormControl( null ),
             city : new FormControl( null, [ Validators.required ] ),
             state : new FormControl( null, [ Validators.required ] ),
             zipCode : new FormControl( null, [ Validators.required ] ),
             country : new FormControl( null, [ Validators.required ] )

    })

     if ( this.titleWindow!=="Create an customer" ) {

          this.customerFrom.patchValue({
            name : this.customer.name,
            email : this.customer.email,
            phone : this.customer.phone,

            zipCode : this.customer.zipCode,
            country : this.customer.country,
            state : this.customer.state,
            city : this.customer.city,
            street : this.customer.street,

          })

     }

  }

  closeWindow(){
    this.visible = false
    this.visibleChange.emit(false)
  }

  sendData(){

    let bodyRequest = {
       name: this.customerFrom.value.name,
       email: this.customerFrom.value.email,
       phone: this.customerFrom.value.phone,
       createAndUpdateAddressDto : {
          street : this.customerFrom.value.street,
          city : this.customerFrom.value.city,
          state : this.customerFrom.value.state,
          zipCode : this.customerFrom.value.zipCode,
          country : this.customerFrom.value.country
    }
  }
    if ( this.titleWindow==="Create an customer" ) {
      this.customerService.save( bodyRequest ).subscribe( {
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
      this.customerService.update( {...bodyRequest, idCustomer : this.customer.id } ).subscribe( {
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

}
