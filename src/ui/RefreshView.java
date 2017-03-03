package ui;

import view.View;

public class RefreshView implements View {
    @Override
    public Object generateView(Object object) {
        RefreshViewModel[] refreshViewModels = (RefreshViewModel[]) object;
        PersonTableModelRecord[] model = new PersonTableModelRecord[refreshViewModels.length];
        int i = 0;
        for (RefreshViewModel record : refreshViewModels)
            model[i++] = addRecord(record);
        return model;
    }

    private PersonTableModelRecord addRecord(RefreshViewModel pr) {
        PersonTableModelRecord modelRecord = new PersonTableModelRecord();
        modelRecord.id = pr.id;
        modelRecord.fullName = pr.fullName;
        modelRecord.occupation = pr.occupation;
        modelRecord.ageCategory = pr.ageCategory;
        modelRecord.employmentStatus = pr.employmentStatus;
        modelRecord.uSCitizen = pr.uSCitizen;
        modelRecord.taxId = pr.taxId;
        modelRecord.gender = pr.gender;
        return modelRecord;
    }
}
