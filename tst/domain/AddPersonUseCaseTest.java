package domain;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.addperson.AddPersonRequest;
import domain.addperson.AddPersonUseCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonUseCaseTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final InputBoundary useCase = new AddPersonUseCase(repository);
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

        for (Person expected : repository.getPeople().values()) {
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
