import { TestBed } from '@angular/core/testing';

import { AddressService } from './AddressServiceService';

describe('AddressServiceService', () => {
  let service: AddressService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddressService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
