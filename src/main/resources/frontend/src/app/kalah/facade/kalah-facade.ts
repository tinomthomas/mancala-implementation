import { Injectable, Injector } from '@angular/core';
import { KalahService } from '../../core/kalah.service';
import { Observable } from 'rxjs';
import { Kalah, Pit, HouseSelectionRequest } from '../model/kalah';

@Injectable({providedIn: 'root'})
export class KalahServiceFacade {

    constructor(private kalahService: KalahService) {}

    initKalah(): Observable<Kalah> {
        return this.kalahService.initKalah();
    }

    fetchKalah(sessionId: string): Observable<Kalah> {
        return this.kalahService.fetchKalah(sessionId);
    }

    selectHouse(houseSelection: HouseSelectionRequest): Observable<Kalah> {
        return this.kalahService.selectHouse(houseSelection);
    }

}