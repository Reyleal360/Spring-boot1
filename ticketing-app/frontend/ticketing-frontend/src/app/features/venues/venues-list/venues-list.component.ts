import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Venue } from '../../core/models/venue.model';
import { VenueService } from '../../core/services/venue.service';

@Component({
    selector: 'app-venues-list',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './venues-list.component.html',
    styleUrls: ['./venues-list.component.scss']
})
export class VenuesListComponent implements OnInit {
    venues: Venue[] = [];
    loading = false;
    error: string | null = null;

    constructor(private venueService: VenueService) { }

    ngOnInit(): void {
        this.loadVenues();
    }

    loadVenues(): void {
        this.loading = true;
        this.error = null;
        this.venueService.getAll().subscribe({
            next: (data) => {
                this.venues = data;
                this.loading = false;
            },
            error: (err) => {
                this.error = 'Error al cargar venues: ' + err.message;
                this.loading = false;
            }
        });
    }

    deleteVenue(id: number | undefined): void {
        if (!id) return;

        if (confirm('¿Estás seguro de eliminar este venue?')) {
            this.venueService.delete(id).subscribe({
                next: () => {
                    this.loadVenues();
                },
                error: (err) => {
                    this.error = 'Error al eliminar venue: ' + err.message;
                }
            });
        }
    }
}
