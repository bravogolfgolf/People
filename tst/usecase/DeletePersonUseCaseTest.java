package usecase;

import database.PersonRepositoryInMemory;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.DeletePersonResponder;
import responder.DeletePersonResponse;

import static org.junit.Assert.assertEquals;

public class DeletePersonUseCaseTest implements DeletePersonResponder {
    private DeletePersonResponse response;

    @Override
    public void present(DeletePersonResponse response) {
        this.response = response;
    }

    @Override
    public int getViewModel() {
        return 0;
    }

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final DeletePersonUseCase useCase = new DeletePersonUseCase(repository, this);
    private final DeletePersonRequest request = new DeletePersonRequest();

    @Before
    public void setUp() {
        repository.addPerson("Full Name1", "Occupation1", 1, 1, true, "111-11-1111", "Male");
        request.id = 1;
    }

    @Test
    public void shouldDeletePersonFromRepository() {
        useCase.execute(request);
        assertEquals(1, response.getId());
    }
}
