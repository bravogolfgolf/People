package ui_swing;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

class SSNMaskFormatter extends MaskFormatter {

    SSNMaskFormatter() throws ParseException {
        super("###-##-####");
        setPlaceholderCharacter('0');
    }
}
