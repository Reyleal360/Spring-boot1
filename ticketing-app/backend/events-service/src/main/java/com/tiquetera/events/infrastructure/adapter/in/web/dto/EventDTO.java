package com.tiquetera.events.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiquetera.events.infrastructure.adapter.in.web.validation.DateRange;
import com.tiquetera.events.infrastructure.adapter.in.web.validation.group.Create;
import com.tiquetera.events.infrastructure.adapter.in.web.validation.group.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para request/response de eventos en la capa REST.
 * 
 * Contiene anotaciones de validación y documentación Swagger.
 * Es parte de la infraestructura, no del dominio.
 * 
 * @author Ticketing Team
 * @version 3.0 - HU5 Error Handling
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para Eventos")
@DateRange(startDate = "eventDate", endDate = "endDate", groups = { Create.class, Update.class })
public class EventDTO {

    @Schema(description = "ID único del evento", example = "1")
    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    private Long id;

    @NotBlank(message = "{event.name.notblank}", groups = { Create.class, Update.class })
    @Size(min = 3, max = 200, message = "{event.name.size}", groups = { Create.class, Update.class })
    @Schema(description = "Nombre del evento", example = "Concierto de Rock 2025", required = true)
    private String name;

    @NotBlank(message = "{event.description.notblank}", groups = { Create.class, Update.class })
    @Size(min = 10, max = 1000, message = "{event.description.size}", groups = { Create.class, Update.class })
    @Schema(description = "Descripción detallada del evento", example = "El mejor concierto de rock del año con bandas internacionales", required = true)
    private String description;

    @NotNull(message = "{event.date.notnull}", groups = { Create.class, Update.class })
    @FutureOrPresent(message = "{event.date.future}", groups = { Create.class, Update.class })
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Fecha y hora de inicio del evento", example = "2025-12-31T20:00:00", required = true)
    private LocalDateTime eventDate;

    @NotNull(message = "{event.date.notnull}", groups = { Create.class, Update.class })
    @FutureOrPresent(message = "{event.date.future}", groups = { Create.class, Update.class })
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Fecha y hora de fin del evento", example = "2025-12-31T23:00:00", required = true)
    private LocalDateTime endDate;

    @NotNull(message = "{event.venue.notnull}", groups = { Create.class, Update.class })
    @Positive(message = "{event.venue.positive}", groups = { Create.class, Update.class })
    @Schema(description = "ID del venue donde se realizará el evento", example = "1", required = true)
    private Long venueId;

    @Schema(description = "Nombre del venue", example = "Estadio Metropolitano")
    private String venueName;

    @NotNull(message = "{event.capacity.notnull}", groups = { Create.class, Update.class })
    @Positive(message = "{event.capacity.positive}", groups = { Create.class, Update.class })
    @Schema(description = "Capacidad total del evento", example = "50000", required = true)
    private Integer capacity;

    @NotNull(message = "{event.price.notnull}", groups = { Create.class, Update.class })
    @DecimalMin(value = "0.0", inclusive = false, message = "{event.price.min}", groups = { Create.class,
            Update.class })
    @Digits(integer = 10, fraction = 2, message = "{event.price.digits}", groups = { Create.class, Update.class })
    @Schema(description = "Precio del boleto", example = "150000.00", required = true)
    private BigDecimal ticketPrice;

    @Schema(description = "Estado del evento", example = "ACTIVE", allowableValues = { "ACTIVE", "CANCELLED",
            "COMPLETED" })
    private String status;

    @Schema(description = "Fecha de creación del registro", example = "2025-01-15T10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Fecha de última actualización", example = "2025-01-20T15:45:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;
}
