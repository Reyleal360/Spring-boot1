import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venue } from '../models/venue.model';

@Injectable({
    providedIn: 'root'
})
export class VenueService {
    private apiUrl = '/api/v1/venues';

    constructor(private http: HttpClient) { }

    getAll(): Observable<Venue[]> {
        return this.http.get<Venue[]>(this.apiUrl);
    }

    getById(id: number): Observable<Venue> {
        return this.http.get<Venue>(`${this.apiUrl}/${id}`);
    }

    create(venue: Venue): Observable<Venue> {
        return this.http.post<Venue>(this.apiUrl, venue);
    }

    update(id: number, venue: Venue): Observable<Venue> {
        return this.http.put<Venue>(`${this.apiUrl}/${id}`, venue);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }

    getByCity(city: string): Observable<Venue[]> {
        return this.http.get<Venue[]>(`${this.apiUrl}/city/${city}`);
    }

    getByCountry(country: string): Observable<Venue[]> {
        return this.http.get<Venue[]>(`${this.apiUrl}/country/${country}`);
    }

    getByStatus(status: string): Observable<Venue[]> {
        return this.http.get<Venue[]>(`${this.apiUrl}/status/${status}`);
    }
}
