package domain;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterTest implements MainFramePresenter {

    private Map<Integer, Person> response;

    @Override
    public void updatePersonTableModel(Map<Integer, Person> response) {
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

        for (Integer key : result.keySet()) {
            assertEquals(result.get(key).getFullName(), response.get(key).getFullName());
            assertEquals(result.get(key).getOccupation(), response.get(key).getOccupation());
            assertEquals(result.get(key).getAgeCategory(), response.get(key).getAgeCategory());
            assertEquals(result.get(key).getEmploymentStatus(), response.get(key).getEmploymentStatus());
            assertTrue(response.get(key).isUsCitizen());
            assertEquals(result.get(key).getTaxId(), response.get(key).getTaxId());
            assertEquals(result.get(key).getGender(), response.get(key).getGender());
        }
    }
}
