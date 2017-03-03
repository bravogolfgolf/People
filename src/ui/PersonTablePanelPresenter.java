package ui;

import response.Presenter;
import response.RefreshResponse;

public class PersonTablePanelPresenter implements Presenter {

    private RefreshViewModel[] viewModel;

    @Override
    public void present(RefreshResponse response) {
        viewModel = new RefreshViewModel[response.getRecords().size()];
        int i = 0;
        for (Object[] object : response.getRecords())
            viewModel[i++] = makeModelRecord(object);
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

    @Override
    public RefreshViewModel[] getViewModel() {
        return viewModel;
    }
}


