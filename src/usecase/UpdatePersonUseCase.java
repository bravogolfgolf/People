package usecase;

import builderfactory.Request;
import builderfactory.UseCase;
import gateway.PersonRepository;
import responder.UpdatePersonResponder;
import responder.UpdatePersonResponse;

public class UpdatePersonUseCase extends UseCase {
    private final PersonRepository repository;
    private final UpdatePersonResponder responder;

    public UpdatePersonUseCase(PersonRepository repository, UpdatePersonResponder responder) {
        this.repository = repository;
        this.responder = responder;
    }

    @Override
    public void execute(Request request) {
        UpdatePersonRequest r = (UpdatePersonRequest) request;
        repository.updatePerson(r.id, r.fullName, r.occupation, r.ageCategory, r.employmentStatus, r.uSCitizen, r.taxId, r.gender);
        UpdatePersonResponse response = new UpdatePersonUseCaseResponse();
        response.setId(r.id);
        responder.present(response);
    }
}
