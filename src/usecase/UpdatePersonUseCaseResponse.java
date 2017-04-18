package usecase;

import responder.UpdatePersonResponse;

public class UpdatePersonUseCaseResponse implements UpdatePersonResponse {
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
