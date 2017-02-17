package ui;

import domain.Person;
import domain.PersonMessage;
import domain.PresenterInteractor;

import java.util.Map;

public class Presenter implements PresenterInteractor {

    private MainFramePresenter mainFrame;

    public void setMainFrame(MainFramePresenter mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void presentPeople(Map<Integer, Person> result) {
        PersonMessage[] response = new PersonMessage[result.size()];
        int i = 0;
        for (Person person : result.values()) {
            PersonMessage message = new PersonMessage();
            message.id = person.getId();
            message.fullName = person.getFullName();
            message.occupation = person.getOccupation();
            message.ageCategory = person.getAgeCategory();
            message.employmentStatus = person.getEmploymentStatus();
            message.uSCitizen = person.isUsCitizen();
            message.taxId = person.getTaxId();
            message.gender = person.getGender();
            response[i++] = message;
        }
        mainFrame.updatePersonTableModel(response);
    }
}


