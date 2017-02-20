package domain;

import data.PersonRepositoryInMemory;
import data.RepositoryInteractor;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshUseCaseTest implements Presenter {

    private Map<Integer, Person> result;

    @Override
    public void present(Map<Integer, Person> result) {
        this.result = result;
    }

    private final RepositoryInteractor repository = new PersonRepositoryInMemory();
    private final UseCase useCase = new RefreshUseCase(repository, this);
    private final Request request = new RefreshRequest();
    private final Person person = new Person(2, "New Full Name",
            "New Occupation", 0, 1,
            true, "New Tax ID", "Female");

    @Before
    public void setUp() {
        repository.addPerson(person);
    }

    @Test
    public void shouldProcessUpdateRequestIntoUpdateResponse() {
        useCase.execute(request);

        for (Person expected : result.values()) {
            assertEquals(person.getId(), expected.getId());
            assertEquals(person.getFullName(), expected.getFullName());
            assertEquals(person.getOccupation(), expected.getOccupation());
            assertEquals(person.getAgeCategory(), expected.getAgeCategory());
            assertEquals(person.getEmploymentStatus(), expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(person.getTaxId(), expected.getTaxId());
            assertEquals(person.getGender(), expected.getGender());
        }
    }


}
