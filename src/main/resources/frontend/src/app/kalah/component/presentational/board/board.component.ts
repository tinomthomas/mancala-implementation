import { Component, ChangeDetectionStrategy, Input, OnChanges, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { Kalah, Player, Pit, PlayerContext, HouseSelectionRequest, Seed, SeedWrapper } from '../../../model/kalah';
import { MatDialog } from '@angular/material/dialog';
import { WinnerComponent } from '../winner/winner.component';

@Component({
  selector: 'board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BoardComponent implements OnChanges {

  @Input() kalah: Kalah;
  @Output() onSelect: EventEmitter<HouseSelectionRequest> = new EventEmitter();
  @Output() onGameEnds: EventEmitter<any> = new EventEmitter();
  topPlayerContext: PlayerContext;
  bottomPlayerContext: PlayerContext;

  constructor(public dialog: MatDialog) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['kalah'].currentValue) {
      this.topPlayerContext = this.buildPlayerContextFor("TOP");
      this.bottomPlayerContext = this.buildPlayerContextFor("BOTTOM");
      this.checkIfGameEnded();
    }
  }

  checkIfGameEnded() {
    if (this.kalah.winner) {
      const dialogRef = this.dialog.open(WinnerComponent, {
        data: {
          winner: this.kalah.winner,
          topPlayerContext: this.topPlayerContext,
          bottomPlayerContext: this.bottomPlayerContext
        },
        minWidth: 250
      });
      dialogRef.beforeClosed().subscribe(r => this.onGameEnds.emit());
      dialogRef.disableClose = true;
    }
  }

  buildPlayerContextFor(position: string): PlayerContext {
    var player = this.kalah.currentPlayer.position == position ? this.kalah.otherPlayer : this.kalah.currentPlayer;
    return {
      player: player,
      houses: this.findHousesOf(player),
      store: this.findStoreOf(player),
      current: this.kalah.currentPlayer.userName == player.userName
    };
  }

  findHousesOf(player: Player): Pit[] {
    return this.kalah.board.pits
      .filter(pit => pit.player.userName == player.userName)
      .filter(pit => !pit.store)
      .map(pit => ({
        ...pit,
        seedWrappers: this.buildSeedWrappers(pit.seeds, 5)
      }));
  }

  buildSeedWrappers(seeds: Seed[], size: number): SeedWrapper[] {
    let seedWrappers: SeedWrapper[] = [];
    let i = 0;
    let degree = 0;
    while (i < seeds.length) {
      seedWrappers.push({
        seeds: seeds.slice(i, i += size),
        degree: 'rotate(' + degree + 'deg)'
      })
      degree += 60;
    }
    return seedWrappers;
  }



  findStoreOf(player: Player): Pit {
    return this.kalah.board.pits.filter(pit => pit.player.userName == player.userName).filter(pit => pit.store).map(pit => ({
      ...pit,
      seedWrappers: this.buildSeedWrappers(pit.seeds, 8)
    }))[0];
  }

  selectHouse(house: Pit) {
    if (house.player.userName === this.kalah.currentPlayer.userName) {
      if (house.seeds.length) {
        this.onSelect.emit({
          sessionId: this.kalah.sessionId,
          house: house
        });
      } else {
        console.log("You cannot select an empty house")
      }
    } else {
      console.log("Not Your turn.")
    }
  }
}
