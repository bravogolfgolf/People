package main;

import domain.Person;
import domain.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;
import ui.RefreshResponse;
import ui.RefreshResponseRecord;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseBuilderImplTest {

    private final ResponseBuilder builder = new ResponseBuilderImpl();
    private final HashMap<Integer, Object> args = new HashMap<>();

    @Before
    public void setUp() {
        Person person = new Person(2, "New Full Name",
                "New Occupation", 0, 1,
                true, "New Tax ID", "Female");
        Map<Integer, Person> map = new HashMap<Integer, Person>() {{
            put(person.getId(), person);
        }};
        args.put(1, map);
    }

    @Test
    public void makeMethodShouldReturnRefreshResponse() {
        RefreshResponse response = (RefreshResponse) builder.make("RefreshResponse", args);
        assertTrue(response != null);

        assertEquals(1, response.responseRecords.length);

        for (RefreshResponseRecord record : response.responseRecords) {
            assertEquals(2, record.id);
            assertEquals("New Full Name", record.fullName);
            assertEquals("New Occupation", record.occupation);
            assertEquals(0, record.ageCategory);
            assertEquals(1, record.employmentStatus);
            assertTrue(record.uSCitizen);
            assertEquals("New Tax ID", record.taxId);
            assertEquals("Female", record.gender);
        }

    }
}
