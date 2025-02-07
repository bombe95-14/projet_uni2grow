import { TestBed } from '@angular/core/testing';

import { AppartServiceService } from './appart-service.service';

describe('AppartServiceService', () => {
  let service: AppartServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppartServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
