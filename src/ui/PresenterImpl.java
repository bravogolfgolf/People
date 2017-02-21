package ui;

import domain.Presenter;
import domain.Response;

public class PresenterImpl implements Presenter {

    private final MainFramePresenter mainFrame;
    private PersonTableModelRecord[] records;

    public PresenterImpl(MainFramePresenter mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void present(Response response) {
        RefreshResponse r = (RefreshResponse) response;
        records = new PersonTableModelRecord[r.people.length];
        transformResponseToRecords(r);
        mainFrame.update(records);
    }

    private void transformResponseToRecords(RefreshResponse r) {
        int i = 0;
        for (String string : r.people) {
            PersonTableModelRecord record = new PersonTableModelRecord();
            String[] splits = string.split("\\|");
            record.id = Integer.parseInt(splits[0]);
            record.fullName = splits[1];
            record.occupation = splits[2];
            record.ageCategory = Integer.parseInt(splits[3]);
            record.employmentStatus = Integer.parseInt(splits[4]);
            record.uSCitizen = Boolean.parseBoolean(splits[5]);
            record.taxId = splits[6];
            record.gender = splits[7];
            records[i++] = record;
        }
    }
}


