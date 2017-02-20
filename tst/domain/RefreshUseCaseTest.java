package domain;

import data.PersonRepositoryInMemory;
import data.RepositoryInteractor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshUseCaseTest implements Presenter {

    private RefreshResponse[] responses;

    @Override
    public void present(RefreshResponse[] responses) {
        this.responses = responses;
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

        for (RefreshResponse response : responses) {
            assertEquals(person.getId(), response.id);
            assertEquals(person.getFullName(), response.fullName);
            assertEquals(person.getOccupation(), response.occupation);
            assertEquals(person.getAgeCategory(), response.ageCategory);
            assertEquals(person.getEmploymentStatus(), response.employmentStatus);
            assertTrue(response.uSCitizen);
            assertEquals(person.getTaxId(), response.taxId);
            assertEquals(person.getGender(), response.gender);
        }
    }
}
