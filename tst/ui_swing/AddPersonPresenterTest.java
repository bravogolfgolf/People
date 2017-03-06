package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.AddPersonResponse;
import responder.View;
import usecase.AddPersonUseCaseResponse;

import static org.junit.Assert.assertEquals;

public class AddPersonPresenterTest {
    private final AddPersonResponse response = new AddPersonUseCaseResponse();

    @Before
    public void setUp() {
        response.setId(1);
    }

    @Test
    public void shouldFormatResponseForView() {
        View view = new AddPersonView();
        AddPersonPresenter presenter = new AddPersonPresenter(view);
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}
