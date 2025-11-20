package com.tiquetera.events.infrastructure.config;

import com.tiquetera.events.application.usecase.*;
import com.tiquetera.events.domain.ports.in.*;
import com.tiquetera.events.domain.ports.out.EventRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CreateEventUseCase createEventUseCase(EventRepositoryPort repo) {
        return new CreateEventService(repo);
    }
    @Bean
    public GetEventUseCase getEventUseCase(EventRepositoryPort repo) {
        return new GetEventService(repo);
    }
    @Bean
    public ListEventsUseCase listEventsUseCase(EventRepositoryPort repo) {
        return new ListEventsService(repo);
    }
    @Bean
    public UpdateEventUseCase updateEventUseCase(EventRepositoryPort repo) {
        return new UpdateEventService(repo);
    }
    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepositoryPort repo) {
        return new DeleteEventService(repo);
    }
}
