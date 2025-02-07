import { FormGroup } from "@angular/forms";

export class GenericValidators {

  constructor(
    private validatorsMessage : { [key : string ] : { [key : string] : string } }
  ) {}

  public createErrorMessage( container : FormGroup ) : {[key : string] : string} {

    const errorsMessages = {};
    for (const controlName in container.controls) {
      if( container.controls.hasOwnProperty(controlName) ){

        const selcetControl = container.controls[controlName];


      }
    }

    return {};
  }

}
