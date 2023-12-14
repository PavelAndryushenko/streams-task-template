package team.mediasoft.study.tasks.streamapi.service;

import lombok.RequiredArgsConstructor;
import team.mediasoft.study.tasks.streamapi.model.Person;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterParameter;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;
import team.mediasoft.study.tasks.streamapi.validation.PersonValidation;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PersonService {

    private final List<Person> persons;

    public List<Person> getPersonsByFilter(SearchFilter filter) {
        PersonValidation.validateFilter(filter);
        return persons.stream()
                .filter(person -> isMatch(person.getLastName(), filter.getLastName()))
                .filter(person -> isMatch(person.getFirstName(), filter.getFirstName()))
                .filter(person -> isMatch(person.getSecondName(), filter.getSecondName()))
                .filter(person -> filter.getAge() == null ||
                        checkAge(person.getBirthDate(), filter.getAge().getValue(), filter.getAge().getOperator()))
                .filter(person -> filter.getBirthPlaceRegion() == null ||
                        person.getBirthPlace().getRegion().contains(filter.getBirthPlaceRegion().getValue()))
                .collect(Collectors.toList());
    }

    private <T extends CharSequence> boolean isMatch(String fieldValue, FilterParameter<T> filterParameter) {
        return filterParameter == null || (fieldValue != null &&
                        (fieldValue.contains(filterParameter.getValue()) &&
                                (filterParameter.getOperator() == FilterOperator.CONTAINS ||
                                        fieldValue.contentEquals(filterParameter.getValue()))));
    }

    private boolean checkAge(LocalDate birthDate, Integer age, FilterOperator operator) {
        final int personAge = Period.between(birthDate, LocalDate.now()).getYears();

        return switch (operator) {
            case EQUALS -> personAge == age;
            case GREATER_THAN -> personAge > age;
            case LESS_THAN -> personAge < age;
            default -> false;
        };
    }
}