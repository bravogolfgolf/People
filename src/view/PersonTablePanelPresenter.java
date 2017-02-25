package view;

import respondor.PersonRecord;
import respondor.Presenter;
import respondor.Response;
import usecase.refresh.RefreshResponse;
import usecase.refresh.RefreshResponseRecord;

public class PersonTablePanelPresenter implements Presenter {

    private PersonRecord[] viewModel;

    @Override
    public void present(Response response) {
        RefreshResponse r = (RefreshResponse) response;
        viewModel = new PersonRecord[r.records.size()];
        int i = 0;
        for (RefreshResponseRecord responseRecord : r.records)
            viewModel[i++] = makeModelRecord(responseRecord);
    }

    private PersonRecord makeModelRecord(RefreshResponseRecord responseRecord) {
        PersonRecord modelRecord = new PersonRecord();
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
    public PersonRecord[] getViewModel() {
        return viewModel;
    }
}


