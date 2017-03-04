package ui_swing;

import responder.AddPersonResponder;
import responder.AddPersonResponse;

public class AddPersonPresenter implements AddPersonResponder {

    private int viewModel;

    @Override
    public void present(AddPersonResponse response) {
        viewModel = response.getId();
    }

    @Override
    public int getViewModel() {
        return viewModel;
    }
}
