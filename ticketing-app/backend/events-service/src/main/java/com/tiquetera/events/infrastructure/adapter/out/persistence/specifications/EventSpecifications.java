package com.tiquetera.events.infrastructure.adapter.out.persistence.specifications;

import com.tiquetera.events.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class EventSpecifications {

    public static Specification<EventEntity> withVenueId(Long venueId) {
        return (root, query, criteriaBuilder) -> {
            if (venueId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("venueId"), venueId);
        };
    }

    public static Specification<EventEntity> withStatus(String status) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(status)) {
                return null;
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("status")), status.toLowerCase());
        };
    }

    public static Specification<EventEntity> withDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null && endDate == null) {
                return null;
            }
            if (startDate != null && endDate != null) {
                return criteriaBuilder.between(root.get("eventDate"), startDate, endDate);
            }
            if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), startDate);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("eventDate"), endDate);
        };
    }

    public static Specification<EventEntity> withFilters(Long venueId, String status, LocalDateTime startDate,
            LocalDateTime endDate) {
        return withVenueId(venueId)
                .and(withStatus(status))
                .and(withDateRange(startDate, endDate));
    }
}
