package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    private Person person1;
    private Person person2;

    @Before
    public void setUp() throws Exception {
        person1 = new Person("Full Name", "Occupation",
                1, 0, true,
                "123-45-6789", "Male");
        person2 = new Person("New Full Name", "New Occupation",
                0, 1, false,
                "New Tax ID", "Female");
    }

    @Test
    public void newPersonShouldHaveAllFieldsDefined() {
        assertTrue(person1.getId() >= 0);
        assertEquals("Full Name", person1.getFullName());
        assertEquals("Occupation", person1.getOccupation());
        assertEquals(1, person1.getAgeCategory());
        assertEquals(0, person1.getEmploymentStatus());
        assertTrue(person1.isUsCitizen());
        assertEquals("123-45-6789", person1.getTaxId());
        assertEquals("Male", person1.getGender());
    }

    @Test
    public void additionalNewPersonShouldIncrementIdByOne() {
        assertEquals(person1.getId() + 1, person2.getId());
    }
}
