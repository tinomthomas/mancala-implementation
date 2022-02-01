import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KalahComponent } from './kalah.component';
import { KalahServiceFacade } from '../../facade/kalah-facade';
import { HttpClientModule } from '@angular/common/http';

describe('KalahComponent', () => {
  let component: KalahComponent;
  let fixture: ComponentFixture<KalahComponent>;
  const kalahFacadeSpy = jasmine.createSpyObj<KalahServiceFacade>('KalahServiceFacade', ['initKalah'])

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [KalahComponent],
      providers: [
        { provide: KalahServiceFacade, useValue: kalahFacadeSpy }
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KalahComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
