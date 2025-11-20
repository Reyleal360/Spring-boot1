import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Venue } from '../../../core/models/venue.model';
import { VenueService } from '../../../core/services/venue.service';

@Component({
    selector: 'app-venue-form',
    standalone: true,
    imports: [CommonModule, RouterModule, FormsModule],
    templateUrl: './venue-form.component.html',
    styleUrls: ['./venue-form.component.scss']
})
export class VenueFormComponent implements OnInit {
    venue: Venue = {
        name: '',
        address: '',
        city: '',
        country: '',
        capacity: 0,
        description: '',
        phone: '',
        email: ''
    };

    isEditMode = false;
    loading = false;
    error: string | null = null;

    constructor(
        private venueService: VenueService,
        private router: Router,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
            this.isEditMode = true;
            this.loadVenue(+id);
        }
    }

    loadVenue(id: number): void {
        this.venueService.getById(id).subscribe({
            next: (data: Venue) => this.venue = data,
            error: (err: any) => this.error = 'Error al cargar venue: ' + err.message
        });
    }

    onSubmit(): void {
        this.loading = true;
        this.error = null;

        const operation = this.isEditMode && this.venue.id
            ? this.venueService.update(this.venue.id, this.venue)
            : this.venueService.create(this.venue);

        operation.subscribe({
            next: () => {
                this.router.navigate(['/venues']);
            },
            error: (err: any) => {
                this.error = 'Error al guardar: ' + err.message;
                this.loading = false;
            }
        });
    }
}
