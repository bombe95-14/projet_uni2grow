import { Observable } from 'rxjs';
import { AddressService } from './../../../service/AddressServiceService';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class AddressComponent implements OnInit, OnDestroy {

  addresses : any[] = [];
  selectedElement: any[]= [];
  keyListElement!: string[];
  titleFormAddAndUpdate : string = ""
  displayFormAddAndUpdate : boolean = false

  observableList : Observable<any>  = new Observable()

  displayDialogCreateAndUpdate : boolean = false;
  displayDialogFilter : boolean = false;

  enaledUpdateButton : boolean = false;
  enabledDeleteButton : boolean = false;

  displayDialogDelete : boolean = false;

  constructor( private addressService : AddressService, private confirmationService : ConfirmationService, private messageService : MessageService  ){}
  ngOnDestroy(): void {


  }

  ngOnInit(): void {

    this.getAddresses()
    this.selectedElement = [];
    this.titleFormAddAndUpdate = "";
    this.displayDialogCreateAndUpdate = false;
    this.displayDialogFilter = false


  }

  onRowSelect(event: any) {
    this.selectedElement = [event.data]
    this.enabledDeleteButton = true;
    this.enaledUpdateButton = true;
    console.log(this.selectedElement);

  }

  onRowUnselect(event: any) {
    this.selectedElement = [];
    console.log(this.selectedElement);
    this.enabledDeleteButton = false;
    this.enaledUpdateButton = false
  }

  displayFormFilter(){
      this.displayDialogFilter = true
  }

  addOrUpdateOneAdress( title : string ){

    this.displayDialogCreateAndUpdate = true;

    if ( title==="add" ) {
      this.titleFormAddAndUpdate="Create an address"
    } else {
      this.titleFormAddAndUpdate = "Update an address"
    }

  }

  deleteOneAddress(event: Event){

    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Are you sure that you want to delete?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      acceptIcon:"none",
      rejectIcon:"none",
      rejectButtonStyleClass:"p-button-text",
      accept: () => {
        this.addressService.deleteOneElement( this.selectedElement[0].id ).subscribe({
          next: (value) => {

            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });
            this.getAddresses();

          }, error(err) {

          },
        })
      },
      reject: () => {
        console.log();
      }
  });

  /* this.confirmationService.confirm({
    header: 'Confirmation',
    message: 'Please confirm to proceed moving forward.',
    acceptIcon: 'pi pi-check mr-2',
    rejectIcon: 'pi pi-times mr-2',
    rejectButtonStyleClass: 'p-button-sm',
    acceptButtonStyleClass: 'p-button-outlined p-button-sm',
    accept: () => {
        this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted', life: 3000 });
    },
    reject: () => {
        this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected', life: 3000 });
    }
}); */

}

  getAddresses(){
    this.addressService.getAllElement().subscribe( {

      next : (value) => {
        this.addresses = value
        if (this.addresses.length > 0) {
          this.keyListElement = Object.keys(this.addresses[0]);
        } else {
          this.keyListElement = [];
        }

      }, error(err) {
          console.log(err);

      },

    } )
  }

  closeAllDialogAndDisabledButton(){
      this.enabledDeleteButton = false;
      this.enaledUpdateButton = false;
      this.displayDialogCreateAndUpdate = false
      this.selectedElement = []
      this.displayDialogFilter = false

  }

  resultOperationsFilter( event : any ){
      this.addresses = event.data;
      this.closeAllDialogAndDisabledButton()
      console.log('filter\n');
      console.log(this.addresses);
      console.log('filter\n');
  }

  resultOperationsCreateAndUpdate(event:any){

  }

}
