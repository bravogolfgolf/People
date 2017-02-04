package domain;

import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class InteractorTest implements PresenterInteractor {

    private Map<Integer, Person> map;

    @Override
    public void addPerson(Map<Integer, Person> map) {
        this.map = map;
    }

    @Test
    public void shouldProcessRequestIntoResult() {
        final PersonRepository repository = new PersonRepository();
        final PresenterInteractor presenter = this;
        final Interactor interactor = new Interactor();
        final Request request = new Request();
        interactor.setRepository(repository);
        interactor.setPresenter(presenter);
        request.fullName = "Full Name";
        request.occupation = "Occupation";
        request.ageCategory = 0;
        request.employmentStatus = 0;
        request.uSCitizen = true;
        request.taxId = "000-00-0000";
        request.gender = "Male";

        interactor.addPerson(request);

        for (Person expected : map.values()) {
            assertEquals(request.fullName, expected.getFullName());
            assertEquals(request.occupation, expected.getOccupation());
            assertEquals(request.ageCategory, expected.getAgeCategory());
            assertEquals(request.employmentStatus, expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(request.taxId, expected.getTaxId());
            assertEquals(request.gender, expected.getGender());
        }
    }
}