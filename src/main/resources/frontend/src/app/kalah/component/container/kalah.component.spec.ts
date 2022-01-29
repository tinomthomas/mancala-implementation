import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KalahComponent } from './kalah.component';

describe('KalahComponent', () => {
  let component: KalahComponent;
  let fixture: ComponentFixture<KalahComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KalahComponent ]
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
