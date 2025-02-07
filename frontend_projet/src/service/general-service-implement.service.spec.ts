import { TestBed } from '@angular/core/testing';

import { GeneralServiceImplementService } from './general-service-implement.service';

describe('GeneralServiceImplementService', () => {
  let service: GeneralServiceImplementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeneralServiceImplementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
