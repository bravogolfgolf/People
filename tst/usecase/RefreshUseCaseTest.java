package usecase;

import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import requestor.Request;
import responder.Presenter;
import responder.RefreshResponse;
import responder.RefreshViewModel;
import usecase.refresh.RefreshRequest;
import usecase.refresh.RefreshUseCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshUseCaseTest implements Presenter {

    private RefreshResponse response;

    @Override
    public void present(RefreshResponse response) {
        this.response = response;
    }

    @Override
    public RefreshViewModel[] getViewModel() {
        return null;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final RefreshUseCase useCase = new RefreshUseCase(repository, this);
    private final Request request = new RefreshRequest();
    private final int ageCategory = 0, employmentStatus = 1;
    private final String fullName = "New Full Name", occupation = "New Occupation", taxId = "New Tax ID", gender = "Female";

    @Before
    public void setUp() {
        repository.addPerson(fullName, occupation, ageCategory, employmentStatus, true, taxId, gender);
    }

    @Test
    public void shouldProcessUpdateRequestIntoUpdateResponse() {
        useCase.execute(request);

        assertEquals(1, response.getRecords().size());

        for (Object[] object : response.getRecords()) {
            assertEquals(1, (int) object[0]);
            assertEquals(fullName, object[1]);
            assertEquals(occupation, object[2]);
            assertEquals(ageCategory, (int) object[3]);
            assertEquals(employmentStatus, (int) object[4]);
            assertTrue((boolean) object[5]);
            assertEquals(taxId, object[6]);
            assertEquals(gender, object[7]);
        }
    }
}
