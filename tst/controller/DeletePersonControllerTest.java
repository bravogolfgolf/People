package controller;

import builderfactory.*;
import database.PersonRepositoryInMemory;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.RefreshResponder;
import responder.View;
import usecase.DeletePersonRequest;
import usecase.DeletePersonUseCase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeletePersonControllerTest {
    private final Map<String, Class> requests = new HashMap<String, Class>() {{
        put("DeletePerson", DeletePersonRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final RefreshResponder presenter = null;
    private final View view = null;
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final int idToDelete = 1;
    private DeletePersonRequest r;

    @Before
    public void setUp() throws Exception {
        repository.addPerson("New Full Name1", "New Occupation1", 1, 1, false, "New Tax ID1", "Male");
        repository.addPerson("New Full Name2", "New Occupation1", 2, 2, true, "New Tax ID2", "Female");
        args.put(0, idToDelete);
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);
        Controller controller = new DeletePersonController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertEquals(idToDelete, r.id);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {
        UseCaseFactoryDummy(Map<String, Class> useCases, Map<String, Class[]> constructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Object responder) {
            return new DeletePersonUseCaseSpy(null, null);
        }
    }

    private class DeletePersonUseCaseSpy extends DeletePersonUseCase {
        DeletePersonUseCaseSpy(PersonRepository repository, RefreshResponder responder) {
            super(repository, responder);
        }

        public void execute(Request request) {
            r = (DeletePersonRequest) request;
        }
    }
}


