package ui_swing;

import responder.ImportResponder;
import responder.ImportResponse;
import responder.View;

public class ImportPresenter implements ImportResponder {
    private final View view;
    private int viewModel;

    public ImportPresenter(View view) {
        this.view = view;
    }

    @Override
    public void present(ImportResponse response) {
        this.viewModel = response.getCount();
    }

    @Override
    public Object generateView() {
        return view.generateView(getViewModel());
    }

    public int getViewModel() {
        return viewModel;
    }
}