import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-and-update-customer',
  templateUrl: './create-and-update-customer.component.html',
  styleUrls: ['./create-and-update-customer.component.css']
})
export class CreateAndUpdateCustomerComponent implements OnInit{

    customerFrom : FormGroup = Object.create(null);
    @Input() titleWindow : string = "";
    @Input() customer : any;
    @Input() visible : boolean = false;

    @Output() visibleChange = new EventEmitter<boolean>();
    constructor(){}


  /* @Input( ) codeTitleWindow : string = "";
  @Input() customer : any; */

  ngOnInit(): void {
     this.customerFrom = new FormGroup({
             name : new FormControl( null, [ Validators.required ] ),
             email : new FormControl( null, [ Validators.required ] ),
             phone : new FormControl( null, [ Validators.required ] )

         })

     if ( this.titleWindow!=="Create an customer" ) {

          this.customerFrom.patchValue({
            street : this.customer.street,
            city : this.customer.city,
            state : this.customer.state,
            zipCode : this.customer.zipCode,
            country : this.customer.country
          })

     }

  }

  closeWindow(){
    this.visible = false
    this.visibleChange.emit(false)
  }

}
