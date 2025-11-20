export interface Event {
    id?: number;
    name: string;
    description: string;
    eventDate: string;
    venueId: number;
    venueName?: string;
    capacity: number;
    ticketPrice: number;
    status?: string;
    createdAt?: string;
    updatedAt?: string;
}
