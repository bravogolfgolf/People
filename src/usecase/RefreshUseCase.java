package usecase;

import gateway.PersonRepository;
import entity.PersonTemplate;
import builderfactory.Request;
import builderfactory.UseCase;
import response.Presenter;

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
        RefreshUseCaseResponse response = new RefreshUseCaseResponse();
        for (Object object : people)
            response.add(createRecord(object));
        presenter.present(response);
    }

    private Object[] createRecord(Object object) {
        Object[] record = new Object[8];
        PersonTemplate person = (PersonTemplate) object;
        record[0] = person.getId();
        record[1] = person.getFullName();
        record[2] = person.getOccupation();
        record[3] = person.getAgeCategory();
        record[4] = person.getEmploymentStatus();
        record[5] = person.isUsCitizen();
        record[6] = person.getTaxId();
        record[7] = person.getGender();
        return record;
    }
}
