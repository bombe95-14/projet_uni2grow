import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAndUpdateInvoiceComponent } from './create-and-update-invoice.component';

describe('CreateAndUpdateInvoiceComponent', () => {
  let component: CreateAndUpdateInvoiceComponent;
  let fixture: ComponentFixture<CreateAndUpdateInvoiceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateAndUpdateInvoiceComponent]
    });
    fixture = TestBed.createComponent(CreateAndUpdateInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
