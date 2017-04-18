package ui_swing;

import responder.Responder;
import responder.UpdatePersonResponder;
import responder.UpdatePersonResponse;
import responder.View;

public class UpdatePersonPresenter implements Responder, UpdatePersonResponder {
    private final View view;
    private int viewModel;

    public UpdatePersonPresenter(View view) {
        this.view = view;
    }

    @Override
    public void present(UpdatePersonResponse response) {
        viewModel = response.getId();
    }

    @Override
    public Object generateView() {
        return view.generateView(viewModel);
    }

    public int getViewModel() {
        return viewModel;
    }
}
