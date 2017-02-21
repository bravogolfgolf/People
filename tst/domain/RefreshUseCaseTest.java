package domain;

import data.PersonRepositoryInMemory;
import data.RepositoryInteractor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshUseCaseTest implements Presenter {

    private Response response;

    @Override
    public void present(Response response) {
        this.response = response;
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

        RefreshResponse r = (RefreshResponse) response;
        assertEquals(1, r.people.length);

        for (String response : r.people) {
            String[] splits = response.split("\\|");
            assertEquals(person.getId(), Integer.parseInt(splits[0]));
            assertEquals(person.getFullName(), splits[1]);
            assertEquals(person.getOccupation(), splits[2]);
            assertEquals(person.getAgeCategory(), Integer.parseInt(splits[3]));
            assertEquals(person.getEmploymentStatus(), Integer.parseInt(splits[4]));
            assertTrue(Boolean.parseBoolean(splits[5]));
            assertEquals(person.getTaxId(), splits[6]);
            assertEquals(person.getGender(), splits[7]);
        }
    }
}
