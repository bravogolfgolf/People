package ui;

import org.junit.Before;
import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterImplTest implements MainFramePresenter {

    private PersonTableModelRecord[] records;

    @Override
    public void update(PersonTableModelRecord[] records) {
        this.records = records;
    }

    private final MainFramePresenter mainFrame = this;
    private final PresenterImpl presenter = new PresenterImpl(mainFrame);
    private final RefreshResponse response = new RefreshResponse();
    private final int id = 1, ageCategory = 1, employmentStatus = 0;
    private final String fullName = "Full Name", occupation = "Occupation", taxId = "123-45-6789", gender = "Male";

    @Before
    public void setUp() throws Exception {
        StringJoiner message = new StringJoiner("|");
        message.add(String.valueOf(id));
        message.add(fullName);
        message.add(occupation);
        message.add(String.valueOf(ageCategory));
        message.add(String.valueOf(employmentStatus));
        message.add(String.valueOf(true));
        message.add(taxId);
        message.add(gender);
        response.people = new String[]{message.toString()};
    }

    @Test
    public void shouldTransformResponseIntoPersonTableModelRecords() {
        presenter.present(response);

        assertEquals(1, records.length);

        assertEquals(id, records[0].id);
        assertEquals(fullName, records[0].fullName);
        assertEquals(occupation, records[0].occupation);
        assertEquals(ageCategory, records[0].ageCategory);
        assertEquals(employmentStatus, records[0].employmentStatus);
        assertTrue(records[0].uSCitizen);
        assertEquals(taxId, records[0].taxId);
        assertEquals(gender, records[0].gender);
    }
}

