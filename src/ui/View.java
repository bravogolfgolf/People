package ui;

import domain.PersonTableModelRecord;

public interface View {
    Object generateView(PersonTableModelRecord[] records);
}
