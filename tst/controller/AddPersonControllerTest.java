package controller;

import builderfactory.Controller;
import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import responder.AddPersonResponder;
import responder.View;
import ui_swing.AddPersonPresenter;
import ui_swing.AddPersonView;
import usecase.AddPersonRequest;
import usecase.AddPersonUseCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonControllerTest {
    private final AddPersonRequest request = new AddPersonRequest();
    private final UseCase useCase = new AddPersonUseCaseSpy(null, null);
    private final AddPersonResponder responder = new AddPersonPresenter();
    private final View view = new AddPersonView();
    private AddPersonRequest r;

    @Before
    public void setUp() throws Exception {
        request.fullName = "Full Name";
        request.occupation = "Occupation";
        request.ageCategory = 0;
        request.employmentStatus = 0;
        request.uSCitizen = true;
        request.taxId = "Tax ID";
        request.gender = "Gender";
    }

    @Test
    public void shouldSendRequestToUseCase() {
        Controller controller = new AddPersonController(request, useCase, responder, view);

        controller.execute();

        assertEquals(request.fullName, r.fullName);
        assertEquals(request.occupation, r.occupation);
        assertEquals(request.ageCategory, r.ageCategory);
        assertEquals(request.employmentStatus, r.employmentStatus);
        assertTrue(r.uSCitizen);
        assertEquals(request.taxId, r.taxId);
        assertEquals(request.gender, r.gender);
    }

    private class AddPersonUseCaseSpy extends AddPersonUseCase {
        AddPersonUseCaseSpy(PersonRepository repository, AddPersonResponder responder) {
            super(repository, responder);
        }

        public void execute(Request request) {
            r = (AddPersonRequest) request;
        }
    }
}

