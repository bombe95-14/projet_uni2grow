import { TestBed } from '@angular/core/testing';

import { ConfigGlobalServiceService } from './config-global-service.service';

describe('ConfigGlobalServiceService', () => {
  let service: ConfigGlobalServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConfigGlobalServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
