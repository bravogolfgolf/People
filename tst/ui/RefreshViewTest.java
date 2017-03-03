package ui;

import org.junit.Before;
import org.junit.Test;
import view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefreshViewTest {
    private RefreshViewModel[] viewModels = new RefreshViewModel[1];

    @Before
    public void setUp() {
        RefreshViewModel viewModel = new RefreshViewModel();
        viewModel.fullName = "Full Name";
        viewModel.occupation = "Occupation";
        viewModel.ageCategory = 0;
        viewModel.employmentStatus = 0;
        viewModel.uSCitizen = true;
        viewModel.taxId = "123-45-6789";
        viewModel.gender = "Male";
        viewModels[0] = viewModel;
    }

    @Test
    public void shouldFormatRefreshViewModelForUi() {
        View refreshView = new RefreshView();
        PersonTableModelRecord[] records = (PersonTableModelRecord[]) refreshView.generateView(viewModels);
        assertEquals(1, records.length);
        for (PersonTableModelRecord record : records) {
            assertEquals(record.fullName, viewModels[0].fullName);
            assertEquals(record.occupation, viewModels[0].occupation);
            assertEquals(record.ageCategory, viewModels[0].ageCategory);
            assertEquals(record.employmentStatus, viewModels[0].employmentStatus);
            assertTrue(record.uSCitizen);
            assertEquals(record.taxId, viewModels[0].taxId);
            assertEquals(record.gender, viewModels[0].gender);
        }
    }
}
