package com.tiquetera.events.infrastructure.adapter.in.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDateTime;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {

    private String startDateField;
    private String endDateField;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        this.startDateField = constraintAnnotation.startDate();
        this.endDateField = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object startDateValue = new BeanWrapperImpl(value).getPropertyValue(startDateField);
        Object endDateValue = new BeanWrapperImpl(value).getPropertyValue(endDateField);

        if (startDateValue == null || endDateValue == null) {
            return true; // Let @NotNull handle nulls
        }

        if (!(startDateValue instanceof LocalDateTime) || !(endDateValue instanceof LocalDateTime)) {
            return false; // Or throw exception
        }

        LocalDateTime start = (LocalDateTime) startDateValue;
        LocalDateTime end = (LocalDateTime) endDateValue;

        return start.isBefore(end);
    }
}
