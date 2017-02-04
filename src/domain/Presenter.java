package domain;

import java.util.HashMap;
import java.util.Map;

public class Presenter implements PresenterInteractor {

    private MainFramePresenter mainFrame;
    private final Map<Integer, Person> response = new HashMap<>();

    public void setMainFrame(MainFramePresenter mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void addPerson(Map<Integer, Person> result) {
        for (Person person : result.values()) {
            response.put(person.getId(), person);
        }
        mainFrame.updatePersonTableModel(response);
    }
}


