package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class ToolBar extends JPanel {

    ToolBar(ToolBarListener listener) {

        // TODO Delete this if not used at end of tutorial
        setVisible(false);

        JButton helloButton = new JButton("Hello");
        helloButton.addActionListener(e -> listener.textEmitted(ToolBar.this.getFormattedText("Hello!")));

        JButton goodbyeButton = new JButton("Goodbye");
        goodbyeButton.addActionListener(e -> listener.textEmitted(ToolBar.this.getFormattedText("Goodbye!")));

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloButton);
        add(goodbyeButton);

        Border insideBorder = BorderFactory.createTitledBorder("ToolBar");
        Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
    }

    private String getFormattedText(String text) {
        return text + MainFrame.NEW_LINE;
    }
}
