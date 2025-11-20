package com.tiquetera.venues.infrastructure.config;

import com.tiquetera.venues.application.usecase.*;
import com.tiquetera.venues.domain.ports.in.*;
import com.tiquetera.venues.domain.ports.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CreateVenueUseCase createVenueUseCase(VenueRepositoryPort repo) {
        return new CreateVenueService(repo);
    }
    @Bean
    public GetVenueUseCase getVenueUseCase(VenueRepositoryPort repo) {
        return new GetVenueService(repo);
    }
    @Bean
    public ListVenuesUseCase listVenuesUseCase(VenueRepositoryPort repo) {
        return new ListVenuesService(repo);
    }
    @Bean
    public UpdateVenueUseCase updateVenueUseCase(VenueRepositoryPort repo) {
        return new UpdateVenueService(repo);
    }
    @Bean
    public DeleteVenueUseCase deleteVenueUseCase(VenueRepositoryPort repo) {
        return new DeleteVenueService(repo);
    }
}
