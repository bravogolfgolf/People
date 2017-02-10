package domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterTest implements MainFramePresenter {

    private PersonMessage[] response;

    @Override
    public void updatePersonTableModel(PersonMessage[] response) {
        this.response = response;
    }

    @Test
    public void shouldTransformResponseIntoMessage() {
        Person.setCounter(0);
        final MainFramePresenter mainFrame = this;
        final Presenter presenter = new Presenter();
        final Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        HashMap<Integer, Person> result = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};
        presenter.setMainFrame(mainFrame);

        presenter.presentPeople(result);

        List<PersonMessage> list = Arrays.asList(response);

        for (Integer key : result.keySet()) {
            assertEquals(result.get(key).getId(), list.get(key).id);
            assertEquals(result.get(key).getFullName(), list.get(key).fullName);
            assertEquals(result.get(key).getOccupation(), list.get(key).occupation);
            assertEquals(result.get(key).getAgeCategory(), list.get(key).ageCategory);
            assertEquals(result.get(key).getEmploymentStatus(), list.get(key).employmentStatus);
            assertTrue(list.get(key).uSCitizen);
            assertEquals(result.get(key).getTaxId(), list.get(key).taxId);
            assertEquals(result.get(key).getGender(), list.get(key).gender);
        }
    }
}
