import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { VenuesListComponent } from './features/venues/venues-list/venues-list.component';
import { VenueFormComponent } from './features/venues/venue-form/venue-form.component';
import { EventsListComponent } from './features/events/events-list/events-list.component';
import { EventFormComponent } from './features/events/event-form/event-form.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'venues', component: VenuesListComponent },
    { path: 'venues/new', component: VenueFormComponent },
    { path: 'venues/:id/edit', component: VenueFormComponent },
    { path: 'events', component: EventsListComponent },
    { path: 'events/new', component: EventFormComponent },
    { path: 'events/:id/edit', component: EventFormComponent },
    { path: '**', redirectTo: '' }
];
