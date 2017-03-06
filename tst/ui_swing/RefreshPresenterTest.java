package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.View;
import usecase.RefreshUseCaseResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshPresenterTest {
    private final RefreshUseCaseResponse response = new RefreshUseCaseResponse();
    private final int id = 1, ageCategory = 1, employmentStatus = 0;
    private final String fullName = "Full Name", occupation = "Occupation", taxId = "123-45-6789", gender = "Male";

    @Before
    public void setUp() throws Exception {
        Object[] record = new Object[8];
        record[0] = id;
        record[1] = fullName;
        record[2] = occupation;
        record[3] = ageCategory;
        record[4] = employmentStatus;
        record[5] = true;
        record[6] = taxId;
        record[7] = gender;
        response.add(record);
    }

    @Test
    public void shouldTransformResponseIntoPersonTableModelRecords() {
        View view = new RefreshView();
        RefreshPresenter presenter = new RefreshPresenter(view);
        presenter.present(response);
        RefreshViewModel[] records = presenter.getViewModel();

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

