package ui_swing;

import org.junit.Before;
import org.junit.Test;
import responder.AddPersonResponder;
import responder.AddPersonResponse;
import usecase.AddPersonUseCaseResponse;

import static org.junit.Assert.assertEquals;

public class AddPersonPresenterTest {

    private final AddPersonResponse response = new AddPersonUseCaseResponse();

    @Before
    public void setUp(){
        response.setId(1);
    }

    @Test
    public void shouldFormatResponseForView() {
        AddPersonResponder presenter = new AddPersonPresenter();
        presenter.present(response);
        int viewModel = presenter.getViewModel();
        assertEquals(1, viewModel);
    }
}
