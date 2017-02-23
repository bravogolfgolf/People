package domain;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.updateperson.UpdatePersonRequest;
import domain.updateperson.UpdatePersonUseCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdatePersonUseCaseTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final InputBoundary refreshUseCase = new RefreshUseCaseDummy();
    private final InputBoundary useCase = new UpdatePersonUseCase(repository, refreshUseCase);
    private final UpdatePersonRequest request = new UpdatePersonRequest();
    private final Person person = new Person(1, "Full Name", "Occupation",
            0, 0, false,
            "000-00-0000", "Male");

    @Before
    public void setUp() {
        repository.addPerson(person);
        request.id = 1;
        request.fullName = "Update Person";
        request.occupation = "Update";
        request.ageCategory = 1;
        request.employmentStatus = 1;
        request.uSCitizen = true;
        request.taxId = "111-11-1111";
        request.gender = "Female";
    }

    @Test
    public void shouldProcessUpdatePersonRequestIntoAddPersonResult() {
        useCase.execute(request);

        assertEquals(1, repository.getPeople().size());

        for (Person expected : repository.getPeople().values()) {
            assertEquals(request.id, expected.getId());
            assertEquals(request.fullName, expected.getFullName());
            assertEquals(request.occupation, expected.getOccupation());
            assertEquals(request.ageCategory, expected.getAgeCategory());
            assertEquals(request.employmentStatus, expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(request.taxId, expected.getTaxId());
            assertEquals(request.gender, expected.getGender());
        }
    }

    private class RefreshUseCaseDummy implements InputBoundary {
        @Override
        public void execute(Request request) {
        }
    }
}
