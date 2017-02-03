package domain;

import java.util.Map;

public interface PresenterInteractor {
    void handle(Map<Integer, Person> response);
}
