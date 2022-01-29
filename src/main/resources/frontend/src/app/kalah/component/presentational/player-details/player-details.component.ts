import { Component, OnInit, Input } from '@angular/core';
import { PlayerContext } from '../../../model/kalah';

@Component({
  selector: 'player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.css']
})
export class PlayerDetailsComponent implements OnInit {

  @Input() playerContext: PlayerContext;

  constructor() { }

  ngOnInit(): void {
  }

}
