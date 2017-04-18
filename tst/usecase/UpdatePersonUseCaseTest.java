package usecase;

import database.PersonRepositoryInMemory;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.UpdatePersonResponder;
import responder.UpdatePersonResponse;

import static org.junit.Assert.assertEquals;

public class UpdatePersonUseCaseTest implements UpdatePersonResponder {

    private UpdatePersonResponse response;

    @Override
    public void present(UpdatePersonResponse response) {
        this.response = response;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final UpdatePersonUseCase useCase = new UpdatePersonUseCase(repository, this);
    private final UpdatePersonRequest request = new UpdatePersonRequest();

    @Before
    public void setUp() {
        repository.addPerson("Full Name", "Occupation", 0, 0, false, "000-00-0000", "Male");
        request.id = 1;
        request.fullName = "Update Person";
        request.occupation = "Update";
        request.ageCategory = 1;
        request.employmentStatus = 1;
        request.uSCitizen = true;
        request.taxId = "111-11-1111";
        request.gender = "Female";
    }

    @Test
    public void shouldProcessUpdatePersonRequestIntoAddPersonResult() {
        useCase.execute(request);

        assertEquals(1, repository.findAll().size());

        assertEquals(request.id, response.getId());
    }
}
