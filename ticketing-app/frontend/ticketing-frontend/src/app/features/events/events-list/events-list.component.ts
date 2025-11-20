import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { Event } from '../../core/models/event.model';
import { EventService } from '../../core/services/event.service';

@Component({
    selector: 'app-events-list',
    standalone: true,
    imports: [CommonModule, RouterModule, HttpClientModule],
    templateUrl: './events-list.component.html',
    styleUrls: ['./events-list.component.scss']
})
export class EventsListComponent implements OnInit {
    events: Event[] = [];
    loading = false;
    error: string | null = null;

    constructor(private eventService: EventService) { }

    ngOnInit(): void {
        this.loadEvents();
    }

    loadEvents(): void {
        this.loading = true;
        this.error = null;
        this.eventService.getAll().subscribe({
            next: (data) => {
                this.events = data;
                this.loading = false;
            },
            error: (err) => {
                this.error = 'Error al cargar eventos: ' + err.message;
                this.loading = false;
            }
        });
    }

    deleteEvent(id: number | undefined): void {
        if (!id) return;

        if (confirm('¿Estás seguro de eliminar este evento?')) {
            this.eventService.delete(id).subscribe({
                next: () => this.loadEvents(),
                error: (err) => this.error = 'Error al eliminar: ' + err.message
            });
        }
    }
}
