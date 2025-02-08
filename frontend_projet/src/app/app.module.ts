import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, UrlSegment } from '@angular/router';
import { InvoiceComponent } from './page/invoice/invoice.component';
import { CustomerComponent } from './page/customer/customer.component';
import { AddressComponent } from './page/address/address.component';
import { HomeComponent } from './home/home.component';
import { CreateAndUpdateAddressComponent } from './page/address/create-and-update-address/create-and-update-address.component';
import { FilterAddressComponent } from './page/address/filter-address/filter-address.component';
import { CreateAndUpdateCustomerComponent } from './page/customer/create-and-update-customer/create-and-update-customer.component';
import { FilterCustomerComponent } from './page/customer/filter-customer/filter-customer.component';
import { CreateAndUpdateInvoiceComponent } from './page/invoice/create-and-update-invoice/create-and-update-invoice.component';
import { FilterInvoiceComponent } from './page/invoice/filter-invoice/filter-invoice.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SplitterModule } from 'primeng/splitter';
import { MenuModule } from 'primeng/menu';
import { GlobalTableComponent } from './generic/global-table/global-table.component';
import { MessagesModule } from 'primeng/messages';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { FieldsetModule } from 'primeng/fieldset';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';

@NgModule({
  declarations: [
    AppComponent,
    InvoiceComponent,
    CustomerComponent,
    AddressComponent,
    HomeComponent,
    CreateAndUpdateAddressComponent,
    FilterAddressComponent,
    CreateAndUpdateCustomerComponent,
    FilterCustomerComponent,
    CreateAndUpdateInvoiceComponent,
    FilterInvoiceComponent,
    GlobalTableComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SplitterModule,
    MenuModule,
    MessagesModule,
    ToastModule,
    TableModule,
    ToolbarModule,
    ButtonModule,
    DialogModule,
    ConfirmDialogModule,
    FieldsetModule,
    InputTextModule,
    DropdownModule,
    RouterModule.forRoot([ ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
