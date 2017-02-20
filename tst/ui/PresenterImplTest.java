package ui;

import domain.MainFramePresenter;
import domain.RefreshResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PresenterImplTest implements MainFramePresenter {

    private RefreshResponse[] responses;

    @Override
    public void update(RefreshResponse[] responses) {
        this.responses = responses;
    }

    private final MainFramePresenter mainFrame = this;
    private final PresenterImpl presenter = new PresenterImpl(mainFrame);
    private final RefreshResponse response = new RefreshResponse();

    @Before
    public void setUp() throws Exception {
        response.id = 1;
        response.fullName = "Full Name";
        response.occupation = "Occupation";
        response.ageCategory = 1;
        response.employmentStatus = 0;
        response.uSCitizen = true;
        response.taxId = "123-45-6789";
        response.gender = "Male";
    }

    @Test
    public void shouldTransformResponseIntoMessage() {
        presenter.present(new RefreshResponse[]{response});

        assertEquals(response.id, responses[0].id);
        assertEquals(response.fullName, responses[0].fullName);
        assertEquals(response.occupation, responses[0].occupation);
        assertEquals(response.ageCategory, responses[0].ageCategory);
        assertEquals(response.employmentStatus, responses[0].employmentStatus);
        assertTrue(responses[0].uSCitizen);
        assertEquals(response.taxId, responses[0].taxId);
        assertEquals(response.gender, responses[0].gender);
    }
}

