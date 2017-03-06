package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import org.junit.Test;
import responder.RefreshResponder;
import responder.View;
import ui_swing.RefreshPresenter;
import usecase.RefreshRequest;
import usecase.RefreshUseCase;

import static org.junit.Assert.assertTrue;

public class RefreshControllerTest {
    private final RefreshRequest request = new RefreshRequest();
    private final UseCase useCase = new RefreshUseCaseSpy(null, null);
    private final RefreshResponder presenter = new RefreshPresenter();
    private final View view = object -> null;
    private RefreshRequest r;

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new RefreshController(request, useCase, presenter, view);

        controller.execute();

        assertTrue(r != null);
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

