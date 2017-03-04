package ui_swing;

import responder.ExportResponder;
import responder.ExportResponse;

public class ExportPresenter implements ExportResponder {
    private int viewModel;

    @Override
    public void present(ExportResponse response) {
        this.viewModel = response.getCount();
    }

    @Override
    public int getViewModel() {
        return viewModel;
    }
}