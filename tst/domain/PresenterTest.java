package domain;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterTest implements MainFramePresenter {

    private Map<Integer, Person> results;

    @Override
    public void updatePersonTableModel(Map<Integer, Person> results) {
        this.results = results;
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

        presenter.handle(response);

        for (Integer key : response.keySet()) {
            assertEquals((response.get(key).getFullName()), (results.get(key)).getFullName());
            assertEquals((response.get(key).getOccupation()), (results.get(key)).getOccupation());
            assertEquals((response.get(key).getAgeCategory()), (results.get(key)).getAgeCategory());
            assertEquals((response.get(key).getEmploymentStatus()), (results.get(key)).getEmploymentStatus());
            assertTrue((results.get(key).isUsCitizen()));
            assertEquals((response.get(key).getTaxId()), (results.get(key)).getTaxId());
            assertEquals((response.get(key).getGender()), (results.get(key)).getGender());
        }
    }
}
