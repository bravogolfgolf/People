package ui;

import domain.Presenter;
import domain.Response;

public class PresenterImpl implements Presenter {

    private final MainFramePresenter mainFrame;

    public PresenterImpl(MainFramePresenter mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void present(Response response) {
        RefreshResponse r = (RefreshResponse) response;
        PersonTableModelRecord[] modelRecords = new PersonTableModelRecord[r.responseRecords.length];
        int i = 0;
        for (RefreshResponseRecord responseRecord : r.responseRecords)
            modelRecords[i++] = makeModelRecord(responseRecord);
        mainFrame.update(modelRecords);
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

}


