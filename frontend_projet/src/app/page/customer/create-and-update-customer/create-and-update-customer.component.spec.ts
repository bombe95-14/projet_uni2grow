import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAndUpdateCustomerComponent } from './create-and-update-customer.component';

describe('CreateAndUpdateCustomerComponent', () => {
  let component: CreateAndUpdateCustomerComponent;
  let fixture: ComponentFixture<CreateAndUpdateCustomerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateAndUpdateCustomerComponent]
    });
    fixture = TestBed.createComponent(CreateAndUpdateCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
