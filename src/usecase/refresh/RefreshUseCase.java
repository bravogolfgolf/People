package usecase.refresh;

import databasegateway.RefreshGateway;
import entity.PersonTemplate;
import requestor.Request;
import requestor.UseCase;
import responder.Presenter;

import java.util.List;

public class RefreshUseCase extends UseCase {

    private final RefreshGateway repository;
    private final Presenter presenter;

    public RefreshUseCase(RefreshGateway repository, Presenter presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        List<PersonTemplate> people = repository.findAll();
        RefreshResponse response = new RefreshResponse();
        for (PersonTemplate person : people)
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
