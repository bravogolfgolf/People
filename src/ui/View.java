package ui;

import domain.PersonRecord;

public interface View {
    Object generateView(PersonRecord[] records);
}
