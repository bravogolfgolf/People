package domain;

import data.PersonRepositoryInMemory;
import data.PersonRepository;
import domain.refresh.RefreshRequest;
import domain.refresh.RefreshUseCase;
import main.ResponseBuilderImpl;
import org.junit.Before;
import org.junit.Test;
import ui.RefreshResponse;
import ui.RefreshResponseRecord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshUseCaseTest implements Presenter {

    private Response response;

    @Override
    public void present(Response response) {
        this.response = response;
    }

    @Override
    public PersonTableModelRecord[] getViewModel() {
        return null;
    }

    private final ResponseBuilder builder = new ResponseBuilderImpl();
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final InputBoundary useCase = new RefreshUseCase(repository, builder, this);
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
        assertEquals(1, r.responseRecords.length);

        for (RefreshResponseRecord response : r.responseRecords) {
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
