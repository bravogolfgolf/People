package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.UpdatePersonResponse;
import responder.View;
import usecase.UpdatePersonUseCaseResponse;

import static org.junit.Assert.assertEquals;

public class UpdatePersonPresenterTest {

    private final UpdatePersonResponse response = new UpdatePersonUseCaseResponse();

    @Before
    public void setUp() {
        response.setId(1);
    }

    @Test
    public void shouldFormatResponseForView() {
        View view = new UpdatePersonView();
        UpdatePersonPresenter presenter = new UpdatePersonPresenter(view);
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}
