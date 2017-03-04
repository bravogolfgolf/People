package usecase;

import responder.ImportResponse;

public class ImportUseCaseResponse implements ImportResponse {
    private int count;

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }
}