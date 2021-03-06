package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.DeletePersonResponder;
import responder.Responder;
import usecase.DeletePersonRequest;
import usecase.DeletePersonUseCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeletePersonControllerTest implements Responder {
    private boolean responderGenerateViewCalled = false;

    public Object generateView() {
        responderGenerateViewCalled = true;
        return null;
    }

    private final DeletePersonRequest request = new DeletePersonRequest();
    private final UseCase useCase = new DeletePersonUseCaseSpy(null, null);
    private final Responder responder = this;
    private final Controller controller = new DeletePersonController(request, useCase, responder);
    private final int idToDelete = 1;
    private DeletePersonRequest r;

    @Before
    public void setUp() {
        request.id = idToDelete;
    }

    @Test
    public void shouldSendRequestToUseCase() {
        controller.execute();

        assertEquals(idToDelete, r.id);
    }

    @Test
    public void shouldCallResponder() {
        controller.execute();

        assertTrue(responderGenerateViewCalled);
    }

    private class DeletePersonUseCaseSpy extends DeletePersonUseCase {
        DeletePersonUseCaseSpy(PersonRepository repository, DeletePersonResponder responder) {
            super(repository, responder);
        }

        public void execute(Request request) {
            r = (DeletePersonRequest) request;
        }
    }
}


