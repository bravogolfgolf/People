package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import entity.PersonTemplate;
import gateway.PersonRepository;
import responder.RefreshResponder;

import java.util.List;

public class RefreshUseCase extends UseCase {

    private final PersonRepository repository;
    private final RefreshResponder responder;

    public RefreshUseCase(PersonRepository repository, RefreshResponder responder) {
        this.repository = repository;
        this.responder = responder;
    }

    @Override
    public void execute(Request request) {
        List people = repository.findAll();
        RefreshUseCaseResponse response = new RefreshUseCaseResponse();
        for (Object object : people)
            response.add(createRecord(object));
        responder.present(response);
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
