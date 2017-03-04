package usecase;

import responder.ExportResponse;

public class ExportUseCaseResponse implements ExportResponse {
    private int count;

    @Override
    public void setId(int count) {
        this.count = count;
    }

    @Override
    public int getId() {
        return count;
    }
}