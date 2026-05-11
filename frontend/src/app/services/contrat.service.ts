import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contrat } from '../models/models';

@Injectable({ providedIn: 'root' })
export class ContratService {
  private apiUrl = '/api/contrats';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(this.apiUrl);
  }

  getById(id: number): Observable<Contrat> {
    return this.http.get<Contrat>(`${this.apiUrl}/${id}`);
  }

  getByClient(clientId: number): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(`${this.apiUrl}/client/${clientId}`);
  }

  create(contrat: Contrat): Observable<Contrat> {
    return this.http.post<Contrat>(this.apiUrl, contrat);
  }

  update(id: number, contrat: Contrat): Observable<Contrat> {
    return this.http.put<Contrat>(`${this.apiUrl}/${id}`, contrat);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  search(keyword: string): Observable<Contrat[]> {
    return this.http.get<Contrat[]>(`${this.apiUrl}/search?keyword=${keyword}`);
  }
}
