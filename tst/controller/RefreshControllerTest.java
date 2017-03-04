package controller;

import builderfactory.*;
import database.PersonRepositoryInMemory;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.RefreshResponder;
import responder.View;
import ui_swing.RefreshPresenter;
import usecase.RefreshRequest;
import usecase.RefreshUseCase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RefreshControllerTest {

    private final Map<String, Class> requests = new HashMap<String, Class>() {{
        put("Refresh", RefreshRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final RefreshResponder presenter = new RefreshPresenter();
    private final View view = object -> null;
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private RefreshRequest r;

    @Before
    public void setUp() {
        repository.addPerson("New Full Name", "New Occupation", 0, 1, false, "New Tax ID", "Female");
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryStub(null, null, null);

        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertTrue(r != null);
    }

    private class UseCaseFactoryStub extends UseCaseFactory {

        UseCaseFactoryStub(Map<String, Class> useCases, Map<String, Class[]> constructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Object responder) {
            return new RefreshUseCaseSpy(null, null);
        }
    }

    private class RefreshUseCaseSpy extends RefreshUseCase {
        RefreshUseCaseSpy(PersonRepository repository, RefreshResponder responder) {
            super(repository, responder);
        }

        public void execute(Request request) {
            r = (RefreshRequest) request;
        }
    }
}

