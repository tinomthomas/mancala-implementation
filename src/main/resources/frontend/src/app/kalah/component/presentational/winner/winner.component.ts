import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { WinnerDialogData } from '../../../model/kalah';

@Component({
  selector: 'app-winner',
  templateUrl: './winner.component.html',
  styleUrls: ['./winner.component.css']
})
export class WinnerComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: WinnerDialogData) {}

}
