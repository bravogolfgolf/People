package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import org.junit.Test;
import responder.RefreshResponder;
import responder.Responder;
import usecase.RefreshRequest;
import usecase.RefreshUseCase;

import static org.junit.Assert.assertTrue;

public class RefreshControllerTest implements Responder {
    private boolean responderGenerateViewCalled = false;

    @Override
    public Object generateView() {
        responderGenerateViewCalled = true;
        return null;
    }

    private final RefreshRequest request = new RefreshRequest();
    private final UseCase useCase = new RefreshUseCaseSpy(null, null);
    private final Responder responder = this;
    private final Controller controller = new RefreshController(request, useCase, responder);

    private RefreshRequest r;

    @Test
    public void shouldSendRequestToUseCase() {
        controller.execute();

        assertTrue(r != null);
    }

    @Test
    public void shouldCallResponder() {
        controller.execute();

        assertTrue(responderGenerateViewCalled);
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

