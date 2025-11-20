import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Event } from '../../../core/models/event.model';
import { Venue } from '../../../core/models/venue.model';
import { EventService } from '../../../core/services/event.service';
import { VenueService } from '../../../core/services/venue.service';

@Component({
    selector: 'app-event-form',
    standalone: true,
    imports: [CommonModule, RouterModule, FormsModule],
    templateUrl: './event-form.component.html',
    styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent implements OnInit {
    event: Event = {
        name: '',
        description: '',
        eventDate: '',
        venueId: 0,
        capacity: 0,
        ticketPrice: 0
    };

    venues: Venue[] = [];
    isEditMode = false;
    loading = false;
    error: string | null = null;

    constructor(
        private eventService: EventService,
        private venueService: VenueService,
        private router: Router,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.loadVenues();
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
            this.isEditMode = true;
            this.loadEvent(+id);
        }
    }

    loadVenues(): void {
        this.venueService.getAll().subscribe({
            next: (data: Venue[]) => this.venues = data,
            error: (err: any) => this.error = 'Error al cargar venues'
        });
    }

    loadEvent(id: number): void {
        this.eventService.getById(id).subscribe({
            next: (data: Event) => this.event = data,
            error: (err: any) => this.error = 'Error al cargar evento'
        });
    }

    onSubmit(): void {
        this.loading = true;
        this.error = null;

        // Actualizar venueName basado en el venueId seleccionado
        // Esto es necesario porque el backend espera el nombre desnormalizado
        const selectedVenue = this.venues.find(v => v.id == this.event.venueId);
        if (selectedVenue) {
            this.event.venueName = selectedVenue.name;
        }

        const operation = this.isEditMode && this.event.id
            ? this.eventService.update(this.event.id, this.event)
            : this.eventService.create(this.event);


        operation.subscribe({
            next: () => this.router.navigate(['/events']),
            error: (err: any) => {
                this.error = 'Error al guardar: ' + err.message;
                this.loading = false;
            }
        });
    }
}
