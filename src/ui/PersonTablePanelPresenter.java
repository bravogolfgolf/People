package ui;

import domain.OutputBoundary;
import domain.PersonTableModelRecord;
import domain.Presenter;
import domain.Response;

public class PersonTablePanelPresenter implements OutputBoundary, Presenter {

    private PersonTableModelRecord[] viewModel;

    @Override
    public void present(Response response) {
        RefreshResponse r = (RefreshResponse) response;
        viewModel = new PersonTableModelRecord[r.responseRecords.length];
        int i = 0;
        for (RefreshResponseRecord responseRecord : r.responseRecords)
            viewModel[i++] = makeModelRecord(responseRecord);
    }

    private PersonTableModelRecord makeModelRecord(RefreshResponseRecord responseRecord) {
        PersonTableModelRecord modelRecord = new PersonTableModelRecord();
        modelRecord.id = responseRecord.id;
        modelRecord.fullName = responseRecord.fullName;
        modelRecord.occupation = responseRecord.occupation;
        modelRecord.ageCategory = responseRecord.ageCategory;
        modelRecord.employmentStatus = responseRecord.employmentStatus;
        modelRecord.uSCitizen = responseRecord.uSCitizen;
        modelRecord.taxId = responseRecord.taxId;
        modelRecord.gender = responseRecord.gender;
        return modelRecord;
    }

    @Override
    public PersonTableModelRecord[] getViewModel() {
        return viewModel;
    }
}


