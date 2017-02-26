package view;

import org.junit.Before;
import org.junit.Test;
import responder.PersonRecord;
import usecase.refresh.RefreshResponse;
import usecase.refresh.RefreshResponseRecord;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTablePanelPresenterTest {

    private final PersonTablePanelPresenter presenter = new PersonTablePanelPresenter();
    private final RefreshResponse response = new RefreshResponse();
    private final int id = 1, ageCategory = 1, employmentStatus = 0;
    private final String fullName = "Full Name", occupation = "Occupation", taxId = "123-45-6789", gender = "Male";

    @Before
    public void setUp() throws Exception {
        RefreshResponseRecord responseRecord = new RefreshResponseRecord();
        responseRecord.id = id;
        responseRecord.fullName = fullName;
        responseRecord.occupation = occupation;
        responseRecord.ageCategory = ageCategory;
        responseRecord.employmentStatus = employmentStatus;
        responseRecord.uSCitizen = true;
        responseRecord.taxId = taxId;
        responseRecord.gender = gender;
        response.records.add(responseRecord);
    }

    @Test
    public void shouldTransformResponseIntoPersonTableModelRecords() {
        presenter.present(response);
        PersonRecord[] records = presenter.getViewModel();

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

