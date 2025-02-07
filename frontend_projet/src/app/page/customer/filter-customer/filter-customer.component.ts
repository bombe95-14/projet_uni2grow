import { CustomerService } from 'src/service/customer-service.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-filter-customer',
  templateUrl: './filter-customer.component.html',
  styleUrls: ['./filter-customer.component.css']
})
export class FilterCustomerComponent implements OnInit {

    customerFilterFrom : FormGroup = Object.create(null);
    @Input() visible : boolean = false;
    @Output() visibleChange = new EventEmitter<boolean>();

    @Output() infoFormApiServer = new EventEmitter<any>();

  constructor( private customerService : CustomerService ) { }

  ngOnInit(): void {

       this.customerFilterFrom = new FormGroup({
                    name : new FormControl( null ),
                    phone : new FormControl( null),
                    email : new FormControl( null  ),
                    state : new FormControl( null ),
                    street : new FormControl( null),
                    country : new FormControl( null  )

                })

  }

  sendDataToServer(  ){

    this.customerService.filterList( this.customerFilterFrom.value ).subscribe({
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
