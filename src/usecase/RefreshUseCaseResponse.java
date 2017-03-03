package usecase;

import response.RefreshResponse;

import java.util.ArrayList;
import java.util.List;

public class RefreshUseCaseResponse implements RefreshResponse {
    private final List<Object[]> records = new ArrayList<>();

    public void add(Object[] record) {
        records.add(record);
    }

    @Override
    public List<Object[]> getRecords() {
        return records;
    }
}
