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
        HashMap<Integer, Person> response = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};
        presenter.setMainFrame(mainFrame);

        presenter.addPerson(response);

        for (Integer key : response.keySet()) {
            assertEquals((response.get(key).getFullName()), (this.response.get(key)).getFullName());
            assertEquals((response.get(key).getOccupation()), (this.response.get(key)).getOccupation());
            assertEquals((response.get(key).getAgeCategory()), (this.response.get(key)).getAgeCategory());
            assertEquals((response.get(key).getEmploymentStatus()), (this.response.get(key)).getEmploymentStatus());
            assertTrue((this.response.get(key).isUsCitizen()));
            assertEquals((response.get(key).getTaxId()), (this.response.get(key)).getTaxId());
            assertEquals((response.get(key).getGender()), (this.response.get(key)).getGender());
        }
    }
}
