package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    @Test
    public void newPersonShouldHaveAllFieldsDefined() {
        Person person = new Person(1, "Full Name", "Occupation",
                1, 0, true,
                "123-45-6789", "Male");
        assertEquals(1, person.getId());
        assertEquals("Full Name", person.getFullName());
        assertEquals("Occupation", person.getOccupation());
        assertEquals(1, person.getAgeCategory());
        assertEquals(0, person.getEmploymentStatus());
        assertTrue(person.isUsCitizen());
        assertEquals("123-45-6789", person.getTaxId());
        assertEquals("Male", person.getGender());
    }
}
