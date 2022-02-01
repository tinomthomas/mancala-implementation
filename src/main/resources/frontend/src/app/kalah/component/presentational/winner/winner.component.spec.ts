import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WinnerComponent } from './winner.component';
import { MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';

describe('WinnerComponent', () => {
  let component: WinnerComponent;
  let fixture: ComponentFixture<WinnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [ WinnerComponent ],
      providers: [{ provide: MAT_DIALOG_DATA, useValue: {
        winner: {"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},
        topPlayerContext: {
          player: {"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},
          houses: [{"seeds":[],"index":1,"player":{"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},"store":false}],
          store: {"seeds":[],"index":7,"player":{"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},"store":true},
          current: false
        },
        bottomPlayerContext: {
          player: {"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},
          houses: [{"seeds":[],"index":1,"player":{"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},"store":false}],
          store: {"seeds":[],"index":7,"player":{"firstName":"John","lastName":"Doe","userName":"johndoe","position":"TOP"},"store":true},
          current: false
        },
      }}]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WinnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
