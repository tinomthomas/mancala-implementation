import { TestBed } from '@angular/core/testing';

import { KalahService } from './kalah.service';
import { HttpClientModule } from '@angular/common/http';

describe('KalahServiceService', () => {
  let service: KalahService;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [HttpClientModule]});
    service = TestBed.inject(KalahService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
