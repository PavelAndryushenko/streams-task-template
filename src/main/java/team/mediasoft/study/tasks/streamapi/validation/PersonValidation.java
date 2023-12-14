package team.mediasoft.study.tasks.streamapi.validation;

import team.mediasoft.study.tasks.streamapi.exception.FilterValidationException;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

public class PersonValidation {
    public static void validateFilter(SearchFilter filter) {
        validateLastName(filter);
        validateFirstName(filter);
        validateSecondName(filter);
        validateAge(filter);
        validateBirthPlaceRegion(filter);
        validateArguments(filter);
    }

    private static void validateLastName(SearchFilter filter) {
        if (filter.getLastName() != null) {
            if (filter.getLastName().getValue() == null) {
                throw new FilterValidationException("Last name cannot be null");
            }
            if (filter.getLastName().getOperator() != FilterOperator.CONTAINS && filter.getLastName().getOperator() != FilterOperator.EQUALS) {
                throw new FilterValidationException("Last name operator must be CONTAINS or EQUALS");
            }
        }
    }

    private static void validateFirstName(SearchFilter filter) {
        if (filter.getFirstName() != null) {
            if (filter.getFirstName().getValue() == null) {
                throw new FilterValidationException("First name cannot be null");
            }
            if (filter.getFirstName().getOperator() != FilterOperator.CONTAINS && filter.getFirstName().getOperator() != FilterOperator.EQUALS) {
                throw new FilterValidationException("First name operator must be CONTAINS or EQUALS");
            }
        }
    }

    private static void validateSecondName(SearchFilter filter) {
        if (filter.getSecondName() != null) {
            if (filter.getSecondName().getValue() == null) {
                throw new FilterValidationException("Second name cannot be null");
            }
            if (filter.getSecondName().getOperator() != FilterOperator.CONTAINS && filter.getSecondName().getOperator() != FilterOperator.EQUALS) {
                throw new FilterValidationException("Second name operator must be CONTAINS or EQUALS");
            }
        }
    }

    private static void validateAge(SearchFilter filter) {
        if (filter.getAge() != null) {
            if (filter.getAge().getValue() == null) {
                throw new FilterValidationException("Age cannot be null");
            }
            if (filter.getAge().getOperator() == FilterOperator.CONTAINS) {
                throw new FilterValidationException("Age operator cannot be CONTAINS");
            }
        }
    }

    private static void validateBirthPlaceRegion(SearchFilter filter) {
        if (filter.getBirthPlaceRegion() != null) {
            if (filter.getBirthPlaceRegion().getValue() == null || filter.getBirthPlaceRegion().getValue().isEmpty()) {
                throw new FilterValidationException("Birth place region cannot be null or empty");
            }
            if (filter.getBirthPlaceRegion().getOperator() != FilterOperator.CONTAINS) {
                throw new FilterValidationException("Birth place region operator must be CONTAINS");
            }
        }
    }

    private static void validateArguments(SearchFilter filter) {
        if (filter.getFirstName() == null && filter.getSecondName() == null && filter.getLastName() == null &&
                filter.getAge() == null && filter.getBirthPlaceRegion() == null) {
            throw new FilterValidationException("Arguments cannot be null");
        }
    }
}
