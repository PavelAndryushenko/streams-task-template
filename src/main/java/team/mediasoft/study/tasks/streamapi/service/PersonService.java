package team.mediasoft.study.tasks.streamapi.service;

import lombok.RequiredArgsConstructor;
import team.mediasoft.study.tasks.streamapi.exception.FilterValidationException;
import team.mediasoft.study.tasks.streamapi.model.Person;
import team.mediasoft.study.tasks.streamapi.model.filter.FilterOperator;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PersonService {

    private final List<Person> persons;

    public List<Person> getPersonsByFilter(SearchFilter filter) {
        final List<Person> filteredPersons = persons.stream()
                .filter(person -> filter.getLastName() == null || person.getLastName().contains(filter.getLastName().getValue()))
                .filter(person -> filter.getFirstName() == null || person.getFirstName().contains(filter.getFirstName().getValue()))
                .filter(person -> filter.getSecondName() == null || (person.getSecondName() != null && person.getSecondName().contains(filter.getSecondName().getValue())))
                .filter(person -> filter.getAge() == null || checkAge(person.getBirthDate(), filter.getAge().getValue(), filter.getAge().getOperator()))
                .filter(person -> filter.getBirthPlaceRegion() == null || person.getBirthPlace().getRegion().contains(filter.getBirthPlaceRegion().getValue()))
                .collect(Collectors.toList());

        if (filteredPersons.isEmpty()) {
            throw new FilterValidationException("Не найдено людей, соответствующих критериям фильтра");
        }

        return filteredPersons;
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