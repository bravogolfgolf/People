package ui_swing;

import responder.DeletePersonResponder;
import responder.DeletePersonResponse;
import responder.Responder;
import responder.View;

public class DeletePersonPresenter implements Responder, DeletePersonResponder {
    private final View view;
    private int viewModel;

    public DeletePersonPresenter(View view) {
        this.view = view;
    }

    @Override
    public void present(DeletePersonResponse response) {
        viewModel = response.getId();
    }

    @Override
    public Object generateView() {
        return view.generateView(getViewModel());
    }

    public int getViewModel() {
        return viewModel;
    }
}
