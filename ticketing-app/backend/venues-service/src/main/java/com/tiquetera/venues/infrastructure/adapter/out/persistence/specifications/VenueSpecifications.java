package com.tiquetera.venues.infrastructure.adapter.out.persistence.specifications;

import com.tiquetera.venues.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class VenueSpecifications {

    public static Specification<VenueEntity> withCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(city)) {
                return null;
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("city")), city.toLowerCase());
        };
    }

    public static Specification<VenueEntity> withCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(country)) {
                return null;
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("country")), country.toLowerCase());
        };
    }

    public static Specification<VenueEntity> withCapacityGreaterThan(Integer capacity) {
        return (root, query, criteriaBuilder) -> {
            if (capacity == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("capacity"), capacity);
        };
    }

    public static Specification<VenueEntity> withStatus(String status) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(status)) {
                return null;
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("status")), status.toLowerCase());
        };
    }
}
