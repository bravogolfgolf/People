package ui_swing;

import responder.ExportResponder;
import responder.ExportResponse;
import responder.View;

public class ExportPresenter implements ExportResponder {
    private final View view;
    private int viewModel;

    public ExportPresenter(View view) {
        this.view = view;
    }

    @Override
    public void present(ExportResponse response) {
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