package view;

import responder.PersonRecord;

public interface View {
    Object generateView(PersonRecord[] records);
}
