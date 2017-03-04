package usecase;

import responder.ExportResponse;

public class ExportUseCaseResponse implements ExportResponse {
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