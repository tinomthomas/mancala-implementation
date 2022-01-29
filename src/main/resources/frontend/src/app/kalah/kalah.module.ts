import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatRippleModule } from '@angular/material/core';
import { BoardComponent } from './component/presentational/board/board.component';
import { KalahComponent } from './component/container/kalah.component';
import { PlayerDetailsComponent } from './component/presentational/player-details/player-details.component';
import { MatDialogModule } from '@angular/material/dialog';
import { WinnerComponent } from './component/presentational/winner/winner.component';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  declarations: [
    KalahComponent,
    BoardComponent,
    PlayerDetailsComponent,
    WinnerComponent
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    MatCardModule,
    MatRippleModule,
    MatDialogModule,
    MatTooltipModule,
    MatButtonModule
  ]
})
export class KalahModule { }
