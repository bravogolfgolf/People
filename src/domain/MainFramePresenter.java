package domain;

import domain.Person;

import java.util.Map;

public interface MainFramePresenter {
    void updatePersonTableModel(Map<Integer, Person> response);
}
