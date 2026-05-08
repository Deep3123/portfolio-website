import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
export interface ContactRequest {
  name: string;
  email: string;
  subject: string;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  sendContactMessage(data: ContactRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/contact`, data);
  }

  recordVisit(): Observable<any> {
    return this.http.post(`${this.baseUrl}/analytics/visit`, {});
  }
}
