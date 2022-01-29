import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { KalahServiceFacade } from '../../facade/kalah-facade';
import { Kalah, Pit, HouseSelectionRequest } from '../../model/kalah';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-kalah',
  templateUrl: './kalah.component.html',
  styleUrls: ['./kalah.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class KalahComponent implements OnInit {

  kalah$: Observable<Kalah>;

  constructor(private kalahFacade: KalahServiceFacade) { }

  ngOnInit(): void {
    this.kalah$ = this.kalahFacade.initKalah();
  }

  selectHouse(houseSelection: HouseSelectionRequest) {
    this.kalah$ = this.kalahFacade.selectHouse(houseSelection);
  }

  resetGame() {
    this.kalah$ = this.kalahFacade.initKalah();
  }
}
