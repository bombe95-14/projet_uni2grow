import { AddressService } from './../../../../service/AddressServiceService';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { InfoResultRequestFromServer } from 'src/app/data/information';

@Component({
  selector: 'app-filter-address',
  templateUrl: './filter-address.component.html',
  styleUrls: ['./filter-address.component.css']
})
export class FilterAddressComponent implements OnInit {

  addressFilterFrom : FormGroup = Object.create(null);
  @Input() visible : boolean = false;
  @Output() visibleChange = new EventEmitter<boolean>();

  @Output() infoFormApiServer = new EventEmitter<any>();

  constructor( private addressService : AddressService ){}

  ngOnInit(): void {

      this.addressFilterFrom = new FormGroup({
              city : new FormControl( null ),
              zipCode : new FormControl( null),
              country : new FormControl( null  )

          })

  }

  sendDataToServer(  ){

      this.addressService.filterList( this.addressFilterFrom.value ).subscribe({
        next: (value) => {
          this.infoFormApiServer.emit(
            {
              data : value,
              message : "success"
            }
           )        }, error(err) {
            console.log('====================================');
            console.log(err);
            console.log('====================================');
        },
      })
  }

  closeDialog(){
      this.visible = false
      this.visibleChange.emit( this.visible )
  }

}
