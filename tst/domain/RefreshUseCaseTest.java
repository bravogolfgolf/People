package domain;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.refresh.RefreshRequest;
import domain.refresh.RefreshResponse;
import domain.refresh.RefreshResponseRecord;
import domain.refresh.RefreshUseCase;
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

    @Override
    public PersonRecord[] getViewModel() {
        return null;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final InputBoundary useCase = new RefreshUseCase(repository, this);
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
        assertEquals(1, r.records.size());

        for (RefreshResponseRecord expected : r.records) {
            assertEquals(person.getId(), expected.id);
            assertEquals(person.getFullName(), expected.fullName);
            assertEquals(person.getOccupation(), expected.occupation);
            assertEquals(person.getAgeCategory(), expected.ageCategory);
            assertEquals(person.getEmploymentStatus(), expected.employmentStatus);
            assertTrue(expected.uSCitizen);
            assertEquals(person.getTaxId(), expected.taxId);
            assertEquals(person.getGender(), expected.gender);
        }
    }
}
