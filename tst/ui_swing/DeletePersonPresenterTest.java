package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.DeletePersonResponse;
import responder.View;
import usecase.DeletePersonUseCaseResponse;

import static org.junit.Assert.assertEquals;

public class DeletePersonPresenterTest {
    private final DeletePersonResponse response = new DeletePersonUseCaseResponse();

    @Before
    public void setUp() {
        response.setId(1);
    }

    @Test
    public void shouldFormatResponseForView() {
        View view = new DeletePersonView();
        DeletePersonPresenter presenter = new DeletePersonPresenter(view);
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}
