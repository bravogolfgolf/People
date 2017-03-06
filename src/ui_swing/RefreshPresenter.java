package ui_swing;

import responder.RefreshResponder;
import responder.RefreshResponse;
import responder.View;

public class RefreshPresenter implements RefreshResponder {
    private final View view;
    private RefreshViewModel[] viewModel;

    public RefreshPresenter(View view) {
        this.view = view;
    }

    @Override
    public void present(RefreshResponse response) {
        viewModel = new RefreshViewModel[response.getRecords().size()];
        int i = 0;
        for (Object[] object : response.getRecords())
            viewModel[i++] = makeModelRecord(object);
    }

    @Override
    public Object generateView() {
        return view.generateView(getViewModel());
    }

    private RefreshViewModel makeModelRecord(Object[] object) {
        RefreshViewModel modelRecord = new RefreshViewModel();
        modelRecord.id = (int) object[0];
        modelRecord.fullName = (String) object[1];
        modelRecord.occupation = (String) object[2];
        modelRecord.ageCategory = (int) object[3];
        modelRecord.employmentStatus = (int) object[4];
        modelRecord.uSCitizen = (boolean) object[5];
        modelRecord.taxId = (String) object[6];
        modelRecord.gender = (String) object[7];
        return modelRecord;
    }

    public RefreshViewModel[] getViewModel() {
        return viewModel;
    }
}


