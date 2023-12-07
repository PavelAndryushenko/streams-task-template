package team.mediasoft.study.tasks.streamapi.service;

import lombok.RequiredArgsConstructor;
import team.mediasoft.study.tasks.streamapi.model.Person;
import team.mediasoft.study.tasks.streamapi.model.filter.SearchFilter;

import java.util.List;

@RequiredArgsConstructor
public class PersonService {

    private final List<Person> persons;

    public List<Person> getPersonsByFilter(SearchFilter filter) {

        // TODO: Логику этого метода необходимо будет реализовать

        return List.of();
    }
}
