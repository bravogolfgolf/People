package ui_swing;

import responder.AddPersonResponder;
import responder.AddPersonResponse;
import responder.View;

public class AddPersonPresenter implements AddPersonResponder {
    private final View view;
    private int viewModel;

    public AddPersonPresenter(View view) {
        this.view = view;
    }

    @Override
    public void present(AddPersonResponse response) {
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
