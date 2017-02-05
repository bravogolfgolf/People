package domain;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ControllerTest implements InteractorController {

    private PersonMessage request;
    private File file;

    @Override
    public void addPerson(PersonMessage request) {
        this.request = request;
    }

    @Override
    public void exportRepository(File file) {
        this.file = file;
    }

    @Override
    public void loadRepository(File file) {
        this.file = file;
    }

    private final Controller controller = new Controller();
    private final InteractorController interactor = this;

    @Before
    public void setUp() {
        controller.setInteractor(interactor);
    }

    @Test
    public void shouldTransformFormEventIntoRequest() {
        FormEvent formEvent = new FormEvent(new Object(), "Full Name", "Occupation", 0, 0, true, "Tax ID", "Gender");

        controller.addPerson(formEvent);

        assertEquals(formEvent.getFullName(), request.fullName);
        assertEquals(formEvent.getOccupation(), request.occupation);
        assertEquals(formEvent.getAgeCategory(), request.ageCategory);
        assertEquals(formEvent.getEmploymentStatus(), request.employmentStatus);
        assertTrue(request.uSCitizen);
        assertEquals(formEvent.getTaxId(), request.taxId);
        assertEquals(formEvent.getGender(), request.gender);
    }

    @Test
    public void verifyExportRepositoryMethodCalled() throws IOException {
        File file = new File("Export.per");
        controller.exportRepository(file);
        assertEquals(this.file.getName(), file.getName());

    }

    @Test
    public void verifyLoadRepositoryMethodCalled() throws IOException {
        File file = new File("Load.per");
        controller.loadRepository(file);
        assertEquals(this.file.getName(), file.getName());

    }
}

