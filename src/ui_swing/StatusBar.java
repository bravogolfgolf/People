package ui_swing;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class StatusBar extends JPanel {

    private JLabel statusLabel = new JLabel();

    StatusBar() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setBorder(new EtchedBorder());
        setPreferredSize(new Dimension(0, 20));
        add(statusLabel, BorderLayout.LINE_START);
        setVisible(true);
    }

    public void setStatusLabel(String statusText){
        statusLabel.setText(statusText);
    }
}
