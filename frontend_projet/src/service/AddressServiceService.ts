import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GeneralServiceImplementService } from './general-service-implement.service';
import { ApiUrlsAddress } from 'src/urls_ressource_backend/ApiUrlsRessourceAddress';


@Injectable({
  providedIn: 'root'
})
export class AddressService extends GeneralServiceImplementService {

  protected override apiUrlToSave : string = ApiUrlsAddress.createOneAddress;
  protected override apiUrlToUpdate : string = ApiUrlsAddress.updateOneAddress;
  protected override apiUrlToDelete : string = ApiUrlsAddress.deleteOneAddress;
  protected override apiUrlList : string = ApiUrlsAddress.listAllAddress;
  protected override apiUrlFilter : string = ApiUrlsAddress.filterAddressesList;

  constructor(protected override httpClient: HttpClient) {
    super(httpClient);
  }

  public saved( url : string, data : any ){
    this.httpClient.post( url, data )
  }
}
