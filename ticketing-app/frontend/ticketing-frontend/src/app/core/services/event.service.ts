import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../models/event.model';

@Injectable({
    providedIn: 'root'
})
export class EventService {
    private apiUrl = '/api/v1/events';

    constructor(private http: HttpClient) { }

    getAll(): Observable<Event[]> {
        return this.http.get<Event[]>(this.apiUrl);
    }

    getById(id: number): Observable<Event> {
        return this.http.get<Event>(`${this.apiUrl}/${id}`);
    }

    create(event: Event): Observable<Event> {
        return this.http.post<Event>(this.apiUrl, event);
    }

    update(id: number, event: Event): Observable<Event> {
        return this.http.put<Event>(`${this.apiUrl}/${id}`, event);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }

    getByVenue(venueId: number): Observable<Event[]> {
        return this.http.get<Event[]>(`${this.apiUrl}/venue/${venueId}`);
    }

    getByStatus(status: string): Observable<Event[]> {
        return this.http.get<Event[]>(`${this.apiUrl}/status/${status}`);
    }
}
