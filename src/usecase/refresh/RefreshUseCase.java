package usecase.refresh;

import databasegateway.PersonRepository;
import entity.PersonTemplate;
import requestor.Request;
import requestor.UseCase;
import responder.Presenter;

import java.util.List;

public class RefreshUseCase extends UseCase {

    private final PersonRepository repository;
    private final Presenter presenter;

    public RefreshUseCase(PersonRepository repository, Presenter presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public void execute(Request request) {
        List people = repository.findAll();
        RefreshResponse response = new RefreshResponse();
        for (Object object : people)
            response.records.add(createRecord(object));
        presenter.present(response);
    }

    private RefreshResponseRecord createRecord(Object object) {
        RefreshResponseRecord record = new RefreshResponseRecord();
        PersonTemplate person = (PersonTemplate) object;
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
