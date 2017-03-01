package usecase;

import database.Person;
import database.PersonRepositoryInMemory;
import databasegateway.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddPersonUseCaseTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final AddPersonUseCase useCase = new AddPersonUseCase(repository, null);
    private final AddPersonRequest request = new AddPersonRequest();

    @Before
    public void setUp() {
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

        assertEquals(1, repository.findAll().size());

        for (Object object : repository.findAll()) {
            Person expected = (Person) object;
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
