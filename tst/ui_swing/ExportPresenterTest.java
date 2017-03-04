package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.ExportResponder;
import responder.ExportResponse;
import usecase.ExportUseCaseResponse;

import static org.junit.Assert.assertEquals;

public class ExportPresenterTest {
    private final ExportResponse response = new ExportUseCaseResponse();

    @Before
    public void setUp() {
        response.setCount(1);
    }

    @Test
    public void shouldFormatResponseForView() {
        ExportResponder presenter = new ExportPresenter();
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}