package domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterTest implements MainFramePresenter {

    private PersonMessage[] response;

    @Override
    public void updatePersonTableModel(PersonMessage[] response) {
        this.response = response;
    }

    @Test
    public void shouldTransformResponseIntoMessage() {
        final MainFramePresenter mainFrame = this;
        final Presenter presenter = new Presenter();
        final Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        HashMap<Integer, Person> result = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};
        presenter.setMainFrame(mainFrame);

        presenter.addPerson(result);

        List<PersonMessage> list = Arrays.asList(response);

        for (Person actual : result.values()) {
            assertEquals(actual.getId(), list.get(0).id);
            assertEquals(actual.getFullName(), list.get(0).fullName);
            assertEquals(actual.getOccupation(), list.get(0).occupation);
            assertEquals(actual.getAgeCategory(), list.get(0).ageCategory);
            assertEquals(actual.getEmploymentStatus(), list.get(0).employmentStatus);
            assertTrue(list.get(0).uSCitizen);
            assertEquals(actual.getTaxId(), list.get(0).taxId);
            assertEquals(actual.getGender(), list.get(0).gender);
        }
    }
}
