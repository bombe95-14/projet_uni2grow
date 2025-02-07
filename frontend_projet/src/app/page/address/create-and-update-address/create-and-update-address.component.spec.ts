import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAndUpdateAddressComponent } from './create-and-update-address.component';

describe('CreateAndUpdateAddressComponent', () => {
  let component: CreateAndUpdateAddressComponent;
  let fixture: ComponentFixture<CreateAndUpdateAddressComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreateAndUpdateAddressComponent]
    });
    fixture = TestBed.createComponent(CreateAndUpdateAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
