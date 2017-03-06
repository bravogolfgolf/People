package usecase;

import database.PersonRepositoryInMemory;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.AddPersonResponder;
import responder.AddPersonResponse;

import static org.junit.Assert.assertEquals;

public class AddPersonUseCaseTest implements AddPersonResponder {
    private AddPersonResponse response;

    @Override
    public void present(AddPersonResponse response) {
        this.response = response;
    }

    @Override
    public Object generateView() {
        return null;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final AddPersonUseCase useCase = new AddPersonUseCase(repository, this);

    private final AddPersonRequest request = new AddPersonRequest();

    @Before
    public void setUp() {
        request.fullName = "Add Person";
        request.occupation = "Occupation";
        request.ageCategory = 0;
        request.employmentStatus = 0;
        request.uSCitizen = true;
        request.taxId = "000-00-0000";
        request.gender = "Male";
    }

    @Test
    public void shouldProcessAddPersonRequestIntoAddPersonResult() {
        useCase.execute(request);
        assertEquals(1, response.getId());
    }
}
