package domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        for (Person actaul : result.values()) {
            assertEquals(actaul.getId(), list.get(0).id);
            assertEquals(actaul.getFullName(), list.get(0).fullName);
            assertEquals(actaul.getOccupation(), list.get(0).occupation);
            assertEquals(actaul.getAgeCategory(), list.get(0).ageCategory);
            assertEquals(actaul.getEmploymentStatus(), list.get(0).employmentStatus);
            assertTrue(list.get(0).uSCitizen);
            assertEquals(actaul.getTaxId(), list.get(0).taxId);
            assertEquals(actaul.getGender(), list.get(0).gender);
        }
    }
}
