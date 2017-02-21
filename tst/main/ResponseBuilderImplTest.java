package main;

import domain.Person;
import ui.RefreshResponse;
import domain.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(1, response.people.length);

        for (String string : response.people) {
            String[] splits = string.split("\\|");
            assertEquals(2, Integer.parseInt(splits[0]));
            assertEquals("New Full Name", splits[1]);
            assertEquals("New Occupation", splits[2]);
            assertEquals(0, Integer.parseInt(splits[3]));
            assertEquals(1, Integer.parseInt(splits[4]));
            assertTrue(Boolean.parseBoolean(splits[5]));
            assertEquals("New Tax ID", splits[6]);
            assertEquals("Female", splits[7]);
        }
    }
}
