package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import responder.AddPersonResponder;
import responder.AddPersonResponse;

public class AddPersonUseCase extends UseCase {
    private final PersonRepository repository;
    private final AddPersonResponder responder;

    public AddPersonUseCase(PersonRepository repository, AddPersonResponder responder) {
        this.repository = repository;
        this.responder = responder;
    }

    @Override
    public void execute(Request request) {
        AddPersonRequest r = (AddPersonRequest) request;
        int id = repository.addPerson(r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        AddPersonResponse response = new AddPersonUseCaseResponse();
        response.setId(id);
        responder.present(response);
    }

}
