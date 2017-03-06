package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.ExportResponse;
import responder.View;
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
        View view = new ExportView();
        ExportPresenter presenter = new ExportPresenter(view);
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}