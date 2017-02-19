package domain;

import data.PersonRepositoryInMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonUseCaseTest implements PresenterInteractor {

    private Map<Integer, Person> result;

    @Override
    public void presentPeople(Map<Integer, Person> result) {
        this.result = result;
    }

    private final PersonRepositoryInMemory repository = new PersonRepositoryInMemory();
    private final AddPersonUseCase useCase = new AddPersonUseCase(repository, this);
    private final AddPersonRequest request = new AddPersonRequest();

    @Before
    public void createRequest() {
        request.fullName = "Add Person";
        request.occupation = "Occupation";
        request.ageCategory = 0;
        request.employmentStatus = 0;
        request.uSCitizen = true;
        request.taxId = "000-00-0000";
        request.gender = "Male";
    }

    @Test
    public void shouldProcessAddPersonRequestIntoAddPersonResult() {
        useCase.execute(request);

        for (Person expected : result.values()) {
            assertEquals(1, expected.getId());
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
