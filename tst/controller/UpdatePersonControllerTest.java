package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import org.junit.Before;
import org.junit.Test;
import responder.Responder;
import usecase.UpdatePersonRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdatePersonControllerTest implements Responder {

    private boolean responderGenerateViewCalled = false;

    @Override
    public Object generateView() {
        responderGenerateViewCalled = true;
        return null;
    }

    private final UpdatePersonRequest request = new UpdatePersonRequest();
    private final UseCase usecase = new UpdatePersonUseCaseSpy();
    private final Controller controller = new UpdatePersonController(request, usecase, this);
    private UpdatePersonRequest r;


    @Before
    public void setUp() {
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
    public void shouldSendRequestToUseCase() {
        controller.execute();
        assertEquals(request.id, r.id);
        assertEquals(request.fullName, r.fullName);
        assertEquals(request.occupation, r.occupation);
        assertEquals(request.ageCategory, r.ageCategory);
        assertEquals(request.employmentStatus, r.employmentStatus);
        assertTrue(r.uSCitizen);
        assertEquals(request.taxId, r.taxId);
        assertEquals(request.gender, r.gender);    }

    @Test
    public void shouldCallResponder() {
        controller.execute();
        assertTrue(responderGenerateViewCalled);
    }

    private class UpdatePersonUseCaseSpy extends UseCase {
        @Override
        public void execute(Request request) {
            r = (UpdatePersonRequest) request;
        }
    }
}
