package domain;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ControllerTest implements InteractorController {

    private Request request;

    @Override
    public void addPerson(Request request) {
        this.request = request;
    }

    @Test
    public void shouldTransformFormEventIntoRequest() {
        InteractorController interactor = this;
        Controller controller = new Controller();
        FormEvent formEvent = new FormEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");
        controller.setInteractor(interactor);

        controller.addPerson(formEvent);

        assertEquals(formEvent.getFullName(),request.fullName);
        assertEquals(formEvent.getOccupation(),request.occupation);
        assertEquals(formEvent.getAgeCategory(),request.ageCategory);
        assertEquals(formEvent.getEmploymentStatus(),request.employmentStatus);
        assertTrue(request.uSCitizen);
        assertEquals(formEvent.getTaxId(),request.taxId);
        assertEquals(formEvent.getGender(),request.gender);
    }
}

