package domain;

import data.PersonRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;
import ui.UseCase;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeletePersonUseCaseTest implements PresenterInteractor {

    private Map<Integer, Person> result;

    @Override
    public void presentPeople(Map<Integer, Person> result) {
        this.result = result;
    }

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final UseCase useCase = new DeletePersonUseCase(repository, this);
    private final DeletePersonRequest request = new DeletePersonRequest();
    private Person person1;

    @Before
    public void setUp() {
        person1 = new Person(1, "Full Name", "Occupation",
                1, 0, true,
                "123-45-6789", "Male");
        Person person2 = new Person(2, "New Full Name",
                "New Occupation", 0, 1,
                false, "New Tax ID", "Female");
        repository.addPerson(person1);
        repository.addPerson(person2);
        request.id = 2;
    }

    @Test
    public void shouldDeletePersonFromRepository() {
        useCase.execute(request);

        assertEquals(1, result.size());
        for (Person expected : result.values()) {
            assertEquals(person1.getId(), expected.getId());
            assertEquals(person1.getFullName(), expected.getFullName());
            assertEquals(person1.getOccupation(), expected.getOccupation());
            assertEquals(person1.getAgeCategory(), expected.getAgeCategory());
            assertEquals(person1.getEmploymentStatus(), expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(person1.getTaxId(), expected.getTaxId());
            assertEquals(person1.getGender(), expected.getGender());
        }
    }
}
