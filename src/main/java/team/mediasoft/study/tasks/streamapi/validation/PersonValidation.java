package team.mediasoft.study.tasks.streamapi.validation;

import lombok.RequiredArgsConstructor;
import team.mediasoft.study.tasks.streamapi.exception.FilterValidationException;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

@RequiredArgsConstructor
public class PersonValidation {
    public static void validateFilter(SearchFilter filter) throws FilterValidationException {
        if (filter.getLastName() != null) {
            if (filter.getLastName().getValue() == null) {
                throw new FilterValidationException("Last name cannot be null");
            }
            if (filter.getLastName().getOperator() != FilterOperator.CONTAINS && filter.getLastName().getOperator() != FilterOperator.EQUALS) {
                throw new FilterValidationException("Last name operator must be CONTAINS or EQUALS");
            }
        }

        if (filter.getFirstName() != null) {
            if (filter.getFirstName().getValue() == null) {
                throw new FilterValidationException("First name cannot be null");
            }
            if (filter.getFirstName().getOperator() != FilterOperator.CONTAINS && filter.getFirstName().getOperator() != FilterOperator.EQUALS) {
                throw new FilterValidationException("First name operator must be CONTAINS or EQUALS");
            }
        }

        if (filter.getSecondName() != null) {
            if (filter.getSecondName().getValue() == null) {
                throw new FilterValidationException("Second name cannot be null");
            }
            if (filter.getSecondName().getOperator() != FilterOperator.CONTAINS && filter.getSecondName().getOperator() != FilterOperator.EQUALS) {
                throw new FilterValidationException("Second name operator must be CONTAINS or EQUALS");
            }
        }

        if (filter.getAge() != null) {
            if (filter.getAge().getValue() == null) {
                throw new FilterValidationException("Age cannot be null");
            }
            if (filter.getAge().getOperator() == FilterOperator.CONTAINS) {
                throw new FilterValidationException("Age operator cannot be CONTAINS");
            }
        }

        if (filter.getBirthPlaceRegion() != null) {
            if (filter.getBirthPlaceRegion().getValue() == null || filter.getBirthPlaceRegion().getValue().isEmpty()) {
                throw new FilterValidationException("Birth place region cannot be null or empty");
            }
            if (filter.getBirthPlaceRegion().getOperator() != FilterOperator.CONTAINS) {
                throw new FilterValidationException("Birth place region operator must be CONTAINS");
            }

        }

        if (filter.getFirstName() == null && filter.getSecondName() == null && filter.getLastName() == null &&
                filter.getAge() == null && filter.getBirthPlaceRegion() == null) {
            throw new FilterValidationException("Arguments cannot be null");
        }
    }
}
