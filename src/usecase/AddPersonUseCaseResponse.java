package usecase;

import responder.AddPersonResponse;

public class AddPersonUseCaseResponse implements AddPersonResponse {
    private int id;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
