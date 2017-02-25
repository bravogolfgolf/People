package usecase;

import database.PersonRepository;
import database.PersonRepositoryInMemory;
import requestor.InputBoundary;
import usecase.updateperson.UpdatePersonRequest;
import usecase.updateperson.UpdatePersonUseCase;
import entity.PersonTemplate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdatePersonUseCaseTest {
    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final InputBoundary refreshUseCase = request -> {
    };
    private final InputBoundary useCase = new UpdatePersonUseCase(repository, refreshUseCase);
    private final UpdatePersonRequest request = new UpdatePersonRequest();

    @Before
    public void setUp() {
        repository.addPerson("Full Name","Occupation",0,0,false,"000-00-0000","Male");
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

        for (PersonTemplate expected : repository.getPeople().values()) {
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
}