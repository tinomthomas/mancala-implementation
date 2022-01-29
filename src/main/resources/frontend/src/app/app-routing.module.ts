import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KalahComponent } from './kalah/component/container/kalah.component';

const routes: Routes = [
  { path: '', component: KalahComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
