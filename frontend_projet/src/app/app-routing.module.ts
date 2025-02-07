import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';/*
import { SearchbarComponent } from './searchbar/searchbar.component';
import { AddAppartmentComponent } from './add-appartment/add-appartment.component';
import { HomeComponent } from './home/home.component';
import { LoginPageComponent } from './login-page/login-page.component'; */
import { HomeComponent } from './home/home.component';
import { AddressComponent } from './page/address/address.component';
import { CustomerComponent } from './page/customer/customer.component';
import { InvoiceComponent } from './page/invoice/invoice.component';

const routes: Routes = [
  { path : "home", component : HomeComponent },
  { path : "address", component : AddressComponent },
  { path : "customer", component : CustomerComponent },
  { path : "invoice", component : InvoiceComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
