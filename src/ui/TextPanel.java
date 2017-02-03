package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class TextPanel extends JPanel {

    private final JTextArea textArea = new JTextArea();

    TextPanel() {
        setupTextPanel();
        addComponentToTextPanel();
    }

    private void setupTextPanel() {
        setLayout(new BorderLayout());
        Border insideBorder = BorderFactory.createTitledBorder("People");
        Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
    }

    private void addComponentToTextPanel() {
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    void appendText(String text){
        textArea.append(text);
    }
}
