import { Injectable } from '@angular/core';
import { GeneralServiceImplementService } from './general-service-implement.service';
import { HttpClient } from '@angular/common/http';
import { ApiUrlsInvoice } from 'src/urls_ressource_backend/ApiUrlsRessourceInvoice';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService extends GeneralServiceImplementService{

  protected override apiUrlToSave : string = ApiUrlsInvoice.createOneINVOICE;
  protected override apiUrlToUpdate : string = ApiUrlsInvoice.updateOneINVOICE;
  protected override apiUrlToDelete : string = ApiUrlsInvoice.deleteOneINVOICE;
  protected override apiUrlList : string = ApiUrlsInvoice.listAllINVOICE;
  protected override apiUrlFilter : string = ApiUrlsInvoice.filterInvoicesList;

  constructor(protected override httpClient: HttpClient) {
    super(httpClient);
  }

}
