package ui_swing;

import responder.ImportResponder;
import responder.ImportResponse;

public class ImportPresenter implements ImportResponder {
    private int viewModel;

    @Override
    public void present(ImportResponse response) {
        this.viewModel = response.getCount();
    }

    @Override
    public int getViewModel() {
        return viewModel;
    }
}