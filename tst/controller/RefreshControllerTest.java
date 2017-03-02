package controller;

import contollerfactory.Controller;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import requestor.Request;
import requestor.RequestBuilder;
import requestor.UseCase;
import requestor.UseCaseFactory;
import responder.Presenter;
import ui.PersonTablePanelPresenter;
import usecase.RefreshRequest;
import usecase.RefreshUseCase;
import view.View;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RefreshControllerTest {

    private final Map<String, Class<?>> requests = new HashMap<String, Class<?>>() {{
        put("Refresh", RefreshRequest.class);
    }};
    private final RequestBuilder requestBuilder = new RequestBuilder(requests);
    private final Map<Integer, Object> args = new HashMap<>();
    private final Presenter presenter = new PersonTablePanelPresenter();
    private final View view = null;
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private RefreshRequest r;

    @Before
    public void setUp() {
        repository.addPerson("New Full Name", "New Occupation", 0, 1, false, "New Tax ID", "Female");
    }

    @Test
    public void shouldSendRequestToUseCase() {
        UseCaseFactory factory = new UseCaseFactoryDummy(null, null, null);

        Controller controller = new RefreshController(requestBuilder, args, factory, presenter, view);

        controller.execute();

        assertTrue(r != null);
    }

    private class UseCaseFactoryDummy extends UseCaseFactory {

        UseCaseFactoryDummy(Map<String, Class<?>> useCases, Map<String, Class<?>[]> constructorClasses, Map<String, Object[]> constructorObjects) {
            super(useCases, constructorClasses, constructorObjects);
        }

        @Override
        public UseCase make(String useCase, Presenter presenter) {
            return new RefreshUseCaseSpy(null, null);
        }
    }

    private class RefreshUseCaseSpy extends RefreshUseCase {
        RefreshUseCaseSpy(PersonRepository repository, Presenter presenter) {
            super(repository, presenter);
        }

        public void execute(Request request) {
            r = (RefreshRequest) request;
        }
    }
}

