package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.ImportResponse;
import responder.View;
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
        View view = new ImportView();
        ImportPresenter presenter = new ImportPresenter(view);
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}