import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Kalah, Pit, HouseSelectionRequest } from '../kalah/model/kalah';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class KalahService {

  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  initKalah() {
    return this.http.post<Kalah>(`${this.apiBaseUrl}/init`, {});
  }

  fetchKalah(sessionId: string) {
    return this.http.get<Kalah>(`${this.apiBaseUrl}/get/${sessionId}`);
  }

  selectHouse(houseSelection: HouseSelectionRequest) {
    return this.http.put<Kalah>(`${this.apiBaseUrl}/select-house`, houseSelection);
  }
}
