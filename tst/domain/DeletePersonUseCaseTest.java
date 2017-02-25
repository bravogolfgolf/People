package domain;

import data.PersonRepository;
import data.PersonRepositoryInMemory;
import domain.deleteperson.DeletePersonRequest;
import domain.deleteperson.DeletePersonUseCase;
import entity.PersonTemplate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeletePersonUseCaseTest {

    private final PersonRepository repository = new PersonRepositoryInMemory();
    private final InputBoundary refreshUseCase = request -> {
    };
    private final InputBoundary useCase = new DeletePersonUseCase(repository, refreshUseCase);
    private final DeletePersonRequest request = new DeletePersonRequest();
    private final int ageCategory = 1, employmentStatus = 1;
    private final String fullName = "Full Name1", occupation = "Occupation1", taxId = "111-11-1111", gender = "Male";

    @Before
    public void setUp() {
        repository.addPerson(fullName, occupation, ageCategory,employmentStatus,true,taxId,gender);
        repository.addPerson("Full Name2", "Occupation2", 2,2,false,"222-22-2222","Female");
        request.id = 2;
    }

    @Test
    public void shouldDeletePersonFromRepository() {
        useCase.execute(request);

        assertEquals(1, repository.getPeople().size());

        assertEquals(1, repository.getPeople().size());
        for (PersonTemplate expected : repository.getPeople().values()) {
            assertEquals(1, expected.getId());
            assertEquals(fullName, expected.getFullName());
            assertEquals(occupation, expected.getOccupation());
            assertEquals(ageCategory, expected.getAgeCategory());
            assertEquals(employmentStatus, expected.getEmploymentStatus());
            assertTrue(expected.isUsCitizen());
            assertEquals(taxId, expected.getTaxId());
            assertEquals(gender, expected.getGender());
        }
    }
}
