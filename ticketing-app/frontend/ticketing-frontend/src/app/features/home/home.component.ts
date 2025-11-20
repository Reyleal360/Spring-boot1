import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EventService } from '../../core/services/event.service';
import { VenueService } from '../../core/services/venue.service';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    totalEvents = 0;
    totalVenues = 0;
    loading = true;

    constructor(
        private eventService: EventService,
        private venueService: VenueService
    ) { }

    ngOnInit(): void {
        this.loadStats();
    }

    loadStats(): void {
        this.eventService.getAll().subscribe({
            next: (events: any) => {
                this.totalEvents = events.length;
                this.loading = false;
            }
        });

        this.venueService.getAll().subscribe({
            next: (venues: any) => this.totalVenues = venues.length
        });
    }
}
