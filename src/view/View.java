package view;

import respondor.PersonRecord;

public interface View {
    Object generateView(PersonRecord[] records);
}
