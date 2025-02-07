import { Injectable } from '@angular/core';
import { GeneralServiceImplementService } from './general-service-implement.service';
import { HttpClient } from '@angular/common/http';
import { ApiUrlsCustomer } from 'src/urls_ressource_backend/ApiUrlsRessourceCustomer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService extends GeneralServiceImplementService {

   protected override apiUrlToSave : string = ApiUrlsCustomer.createOneCUSTOMER;
    protected override apiUrlToUpdate : string = ApiUrlsCustomer.updateOneCUSTOMER;
    protected override apiUrlToDelete : string = ApiUrlsCustomer.deleteOneCUSTOMER;
    protected override apiUrlList : string = ApiUrlsCustomer.listAllCUSTOMER;
    protected override apiUrlFilter : string = ApiUrlsCustomer.filterCustomersList;

 constructor(protected override httpClient: HttpClient) {
    super(httpClient);
  }

}
