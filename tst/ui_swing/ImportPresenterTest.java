package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.ImportResponder;
import responder.ImportResponse;
import usecase.ImportUseCaseResponse;

import static org.junit.Assert.assertEquals;

public class ImportPresenterTest {
    private final ImportResponse response = new ImportUseCaseResponse();

    @Before
    public void setUp() {
        response.setCount(1);
    }

    @Test
    public void shouldFormatResponseForView() {
        ImportResponder presenter = new ImportPresenter();
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}