package usecase.refresh;

import databasegateway.RefreshGateway;
import requestor.InputBoundary;
import requestor.Request;
import respondor.Presenter;
import entity.PersonTemplate;

import java.util.Map;

public class RefreshUseCase implements InputBoundary {

    private final RefreshGateway repository;
    private final Presenter presenter;

    public RefreshUseCase(RefreshGateway repository, Presenter presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        Map<Integer, PersonTemplate> people = repository.getPeople();
        RefreshResponse response = new RefreshResponse();
        for (PersonTemplate person : people.values())
            response.records.add(createRecord(person));
        presenter.present(response);
    }

    private RefreshResponseRecord createRecord(PersonTemplate person) {
        RefreshResponseRecord record = new RefreshResponseRecord();
        record.id = person.getId();
        record.fullName = person.getFullName();
        record.occupation = person.getOccupation();
        record.ageCategory = person.getAgeCategory();
        record.employmentStatus = person.getEmploymentStatus();
        record.uSCitizen = person.isUsCitizen();
        record.taxId = person.getTaxId();
        record.gender = person.getGender();
        return record;
    }
}