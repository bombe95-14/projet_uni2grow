import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigGlobalServiceService {

  private url = "http://localhost:3000";
  constructor( private httpClient: HttpClient ) { }

  getEleves() : Observable<any>{
    return this.httpClient.get<any>(this.url + "/eleves");
  }
}
