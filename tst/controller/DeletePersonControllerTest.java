package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.DeletePersonResponder;
import responder.View;
import ui_swing.DeletePersonPresenter;
import ui_swing.DeletePersonView;
import usecase.DeletePersonRequest;
import usecase.DeletePersonUseCase;

import static org.junit.Assert.assertEquals;

public class DeletePersonControllerTest {
    private final DeletePersonRequest request = new DeletePersonRequest();
    private final UseCase useCase = new DeletePersonUseCaseSpy(null, null);
    private final DeletePersonResponder responder = new DeletePersonPresenter();
    private final View view = new DeletePersonView();
    private final int idToDelete = 1;
    private DeletePersonRequest r;

    @Before
    public void setUp() throws Exception {
        request.id = idToDelete;
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new DeletePersonController(request, useCase, responder, view);

        controller.execute();

        assertEquals(idToDelete, r.id);
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


