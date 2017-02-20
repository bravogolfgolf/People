package ui;

import domain.AddPersonRequest;
import domain.MainFramePresenter;
import domain.Person;
import org.junit.Test;
import ui.PresenterImpl;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterImplTest implements MainFramePresenter {

    private AddPersonRequest[] response;

    @Override
    public void update(AddPersonRequest[] response) {
        this.response = response;
    }

    @Test
    public void shouldTransformResponseIntoMessage() {
        final MainFramePresenter mainFrame = this;
        final PresenterImpl presenter = new PresenterImpl(mainFrame);
        final Person person = new Person(1, "Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        HashMap<Integer, Person> result = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};

        presenter.present(result);

        assertEquals(person.getId(), response[0].id);
        assertEquals(person.getFullName(), response[0].fullName);
        assertEquals(person.getOccupation(), response[0].occupation);
        assertEquals(person.getAgeCategory(), response[0].ageCategory);
        assertEquals(person.getEmploymentStatus(), response[0].employmentStatus);
        assertTrue(response[0].uSCitizen);
        assertEquals(person.getTaxId(), response[0].taxId);
        assertEquals(person.getGender(), response[0].gender);
    }
}

