package ui_swing;

import response.View;

public class PersonTablePanelView implements View {

    @Override
    public Object generateView(Object object) {
        RefreshViewModel[] refreshViewModels = (RefreshViewModel[]) object;
        PersonTableModelRecord[] model = new PersonTableModelRecord[refreshViewModels.length];
        int i = 0;
        for (RefreshViewModel record : refreshViewModels)
            model[i++] = addRecord(record);
        return model;
    }

    private PersonTableModelRecord addRecord(RefreshViewModel refreshViewModel) {
        PersonTableModelRecord modelRecord = new PersonTableModelRecord();
        modelRecord.id = refreshViewModel.id;
        modelRecord.fullName = refreshViewModel.fullName;
        modelRecord.occupation = refreshViewModel.occupation;
        modelRecord.ageCategory = refreshViewModel.ageCategory;
        modelRecord.employmentStatus = refreshViewModel.employmentStatus;
        modelRecord.uSCitizen = refreshViewModel.uSCitizen;
        modelRecord.taxId = refreshViewModel.taxId;
        modelRecord.gender = refreshViewModel.gender;
        return modelRecord;
    }
}
