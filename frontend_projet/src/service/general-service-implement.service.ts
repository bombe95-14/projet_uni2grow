import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeneralServiceImplementService {

  protected apiUrlToSave : string = "";
  protected apiUrlToUpdate : string = "";
  protected apiUrlToDelete : string = "";
  protected apiUrlList : string = "";
  protected apiUrlFilter : string = "";

  constructor( protected httpClient : HttpClient ) { }

  public save( data : any ) : Observable<any> {

    console.log('====================================');
    console.log("test backend \n  " + this.apiUrlToSave);
    console.log(data);
    return this.httpClient.post( this.apiUrlToSave, data );

  }

  public update( data : any ) : Observable<any> {

    return this.httpClient.put( this.apiUrlToUpdate, data );

  }

  public getAllElement(  ) : Observable<any> {

    return this.httpClient.get(this.apiUrlList);

  }

  public filterList( data : any  ) : Observable<any> {
    return this.httpClient.post( this.apiUrlFilter, data );
  }

  public deleteOneElement( id : number  ) : Observable<any> {
    return this.httpClient.delete( this.apiUrlToDelete + id );
  }
}
