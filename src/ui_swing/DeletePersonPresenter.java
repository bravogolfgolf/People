package ui_swing;

import responder.DeletePersonResponder;
import responder.DeletePersonResponse;

public class DeletePersonPresenter implements DeletePersonResponder {

    private int viewModel;

    @Override
    public void present(DeletePersonResponse response) {
        viewModel = response.getId();
    }

    @Override
    public int getViewModel() {
        return viewModel;
    }
}
