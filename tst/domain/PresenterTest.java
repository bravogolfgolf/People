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
        final Response response = new Response();
        final Person person = new Person("Full Name", "Occupation", 1, 0, true, "123-45-6789", "Male");
        response.people = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};
        presenter.setMainFrame(mainFrame);

        presenter.handle(response);

        for (Integer key : response.people.keySet()) {
            assertEquals((response.people.get(key).getFullName()), (results.get(key)).getFullName());
            assertEquals((response.people.get(key).getOccupation()), (results.get(key)).getOccupation());
            assertEquals((response.people.get(key).getAgeCategory()), (results.get(key)).getAgeCategory());
            assertEquals((response.people.get(key).getEmploymentStatus()), (results.get(key)).getEmploymentStatus());
            assertTrue((results.get(key).isUsCitizen()));
            assertEquals((response.people.get(key).getTaxId()), (results.get(key)).getTaxId());
            assertEquals((response.people.get(key).getGender()), (results.get(key)).getGender());
        }
    }
}
