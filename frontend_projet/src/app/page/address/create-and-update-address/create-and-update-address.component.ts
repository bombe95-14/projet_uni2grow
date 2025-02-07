import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AddressService } from './../../../../service/AddressServiceService';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';


@Component({
  selector: 'app-create-and-update-address',
  templateUrl: './create-and-update-address.component.html',
  styleUrls: ['./create-and-update-address.component.css'],
  providers: [ConfirmationService, MessageService]

})
export class CreateAndUpdateAddressComponent implements OnInit {

  addressFrom : FormGroup = Object.create(null);

  @Input() titleWindow : string = "";
  @Input() address : any;
  @Input() visible : boolean = false;

  @Output() visibleChange = new EventEmitter<boolean>();
  @Output() infoFormApiServer = new EventEmitter<any>();

  constructor( private addressService : AddressService, private confirmationService : ConfirmationService, private messageService : MessageService  ){}

  ngOnInit(): void {

    this.addressFrom = new FormGroup({
        street : new FormControl( null, [ Validators.required ] ),
        city : new FormControl( null, [ Validators.required ] ),
        state : new FormControl( null, [ Validators.required ] ),
        zipCode : new FormControl( null, [ Validators.required ] ),
        country : new FormControl( null, [ Validators.required ] )

    })

    if ( this.titleWindow!=="Create an address" ) {

      this.addressFrom.patchValue({
        street : this.address.street,
        city : this.address.city,
        state : this.address.state,
        zipCode : this.address.zipCode,
        country : this.address.country
      })

    }

  }

  sendData(){

    if ( this.titleWindow==="Create an address" ) {
      this.addressService.save( this.addressFrom.value ).subscribe( {
        next: (value) => {
                this.visible=false
                this.visibleChange.emit(this.visible)
               this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });
               this.infoFormApiServer.emit(
                {
                  data : "create success"
                }
               )
        }, error : (err) => {

          this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });

        },
      } );
    }  else {
      const dataFromForm = this.addressFrom.value;
      this.addressService.update( {...dataFromForm, idAdress : this.address.id } ).subscribe( {
        next: (value) => {
          this.visible=false
          this.visibleChange.emit(this.visible)
         this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Operation successfully completed', life: 3000 });
         this.infoFormApiServer.emit(
          {
            data : "update success"
          }
         )
       }, error : (err) => {

          this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });

        },
      } );
    }

  }

  closeWindow(){
      this.visible = false
      this.visibleChange.emit(false)
  }

}
