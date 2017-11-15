package ui_swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

class EntryPanel extends JPanel {

    private int id;
    private final EntryPanelListener entryPanelListener;
    private final JLabel nameLabel = new JLabel("Full Name:");
    private final JTextField nameField = new JTextField(10);
    private final JLabel occupationLabel = new JLabel("Occupation:");
    private final JTextField occupationField = new JTextField(10);
    private final JLabel ageLabel = new JLabel("Age Group:");
    private final DefaultListModel<String> ageModel = new DefaultListModel<>();
    private final JList<String> ageList = new JList<>(ageModel);
    private final JLabel statusLabel = new JLabel("Status:");
    private final DefaultComboBoxModel<String> statusModel = new DefaultComboBoxModel<>();
    private final JComboBox<String> statusCombo = new JComboBox<>(statusModel);
    private final JLabel uSCitizenLabel = new JLabel("US Citizen:");
    private final JCheckBox uSCitizenCheckBox = new JCheckBox();
    private final JLabel taxIdLabel = new JLabel("Tax ID:");
    private JFormattedTextField taxIdField;
    private final JLabel genderLabel = new JLabel("Gender:");
    private final JRadioButton maleRadioButton = new JRadioButton("Male", true);
    private final JRadioButton femaleRadioButton = new JRadioButton("Female");
    private final ButtonGroup genderButtonGroup = new ButtonGroup();
    private final JPanel radioButtonPanel = new JPanel(new FlowLayout());
    private final JButton deleteButton = new JButton("Delete");
    private final JButton updateButton = new JButton("Update");
    private final JButton addButton = new JButton("Add");
    private final JPanel paddingPanel = new JPanel();
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    EntryPanel(EntryPanelListener entryPanelListener) {
        this.entryPanelListener = entryPanelListener;
        setupComponents();
        addComponentsToEntryPanel();
    }

    private void setupComponents() {
        setupEntryPanel();
        setupAgeList();
        setupStatusCombo();
        setUpUsCitizenCheckBox();
        setupTaxIdLabel();
        setupTaxIdField();
        setupGenderRadioButtons();
        setupAddButton();
        setupUpdateButton();
        setupDeleteButton();
    }

    private void setupEntryPanel() {
        Border insideBorder = BorderFactory.createTitledBorder("Person");
        Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setLayout(new GridBagLayout());
    }

    private void setupAgeList() {
        ageModel.addElement("Under 18");
        ageModel.addElement("18 to 65");
        ageModel.addElement("Over 65");
        ageList.setSelectedIndex(1);
        ageList.setBorder(BorderFactory.createEtchedBorder());
    }

    private void setupStatusCombo() {
        statusModel.addElement("Employed");
        statusModel.addElement("Self-employed");
        statusModel.addElement("Unemployed");
        statusCombo.setSelectedIndex(0);
    }

    private void setUpUsCitizenCheckBox() {
        uSCitizenCheckBox.addActionListener(e -> {
            taxIdLabel.setEnabled(uSCitizenCheckBox.isSelected());
            taxIdLabel.setVisible(uSCitizenCheckBox.isSelected());
            taxIdField.setEnabled(uSCitizenCheckBox.isSelected());
            taxIdField.setVisible(uSCitizenCheckBox.isSelected());
        });
    }

    private void setupTaxIdLabel() {
        taxIdLabel.setEnabled(false);
        taxIdLabel.setVisible(false);
    }

    private void setupTaxIdField() {
        SSNMaskFormatter ssnFormatter = tryGetMaskFormatter();
        taxIdField = new JFormattedTextField(ssnFormatter);
        taxIdField.setEnabled(false);
        taxIdField.setVisible(false);
    }

    private SSNMaskFormatter tryGetMaskFormatter() {
        SSNMaskFormatter ssnFormatter = null;
        try {
            ssnFormatter = new SSNMaskFormatter();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ssnFormatter;
    }

    private void setupGenderRadioButtons() {
        maleRadioButton.setActionCommand("Male");
        femaleRadioButton.setActionCommand("Female");
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
        radioButtonPanel.add(maleRadioButton);
        radioButtonPanel.add(femaleRadioButton);
    }

    private void setupAddButton() {
        addButton.addActionListener(e -> {
            emitAddEvent(e);
            resetEventPanel();
        });
    }

    private void setupUpdateButton() {
        updateButton.setEnabled(false);
        updateButton.addActionListener(e -> {
            emitUpdateEvent(e);
            resetEventPanel();
        });
    }

    private void setupDeleteButton() {
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> {
            emitDeleteEvent(e);
            resetEventPanel();
        });
    }

    private void emitAddEvent(ActionEvent e) {
        entryPanelListener.addEventEmitted(new EntryPanelAddEvent(e, nameField.getText(), occupationField.getText(), ageList.getSelectedIndex(), statusCombo.getSelectedIndex(), uSCitizenCheckBox.isSelected(), getTaxId(), genderButtonGroup.getSelection().getActionCommand()));
    }

    private void emitUpdateEvent(ActionEvent e) {
        entryPanelListener.updateEventEmitted(new EntryPanelUpdateEvent(e, id, nameField.getText(), occupationField.getText(), ageList.getSelectedIndex(), statusCombo.getSelectedIndex(), uSCitizenCheckBox.isSelected(), getTaxId(), genderButtonGroup.getSelection().getActionCommand()));
    }

    private void emitDeleteEvent(ActionEvent e) {
        entryPanelListener.deleteEventEmitted(new EntryPanelDeleteEvent(e, id));
    }

    private String getTaxId() {
        return uSCitizenCheckBox.isSelected() ? (String) taxIdField.getValue() : "000-00-0000";
    }

    void rowSelected(int id, String fullName, String occupation, int ageCategory, int employmentStatus, boolean uSCitizen, String taxId, String gender) {
        updateButton.setEnabled(true);
        deleteButton.setEnabled(true);
        this.id = id;
        nameField.setText(fullName);
        occupationField.setText(occupation);
        ageList.setSelectedIndex(ageCategory);
        statusCombo.setSelectedIndex(employmentStatus);
        uSCitizenCheckBox.setSelected(uSCitizen);

        for (ActionListener actionListener : uSCitizenCheckBox.getActionListeners()) {
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
            });
        }

        taxIdField.setText(taxId);
        if (gender.equals("Male"))
            maleRadioButton.setSelected(true);
        else
            femaleRadioButton.setSelected(true);
    }

    private void resetEventPanel() {
        deleteButton.setEnabled(false);
        updateButton.setEnabled(false);
        id = 0;
        nameField.setText("");
        occupationField.setText("");
        ageList.setSelectedIndex(1);
        statusCombo.setSelectedIndex(0);
        uSCitizenCheckBox.setSelected(false);
        taxIdField.setText("");

        for (ActionListener listener : uSCitizenCheckBox.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        maleRadioButton.setSelected(true);
    }

    private void addComponentsToEntryPanel() {

        setGridBagConstraints(0, 0, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(nameLabel, gridBagConstraints);

        setGridBagConstraints(0, 1, 2, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(nameField, gridBagConstraints);

        setGridBagConstraints(1, 0, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(occupationLabel, gridBagConstraints);

        setGridBagConstraints(1, 1, 2, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(occupationField, gridBagConstraints);

        setGridBagConstraints(2, 0, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
        gridBagConstraints.insets = new Insets(3, 0, 0, 0);
        add(ageLabel, gridBagConstraints);

        setGridBagConstraints(2, 1, 2, 3, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(ageList, gridBagConstraints);

        setGridBagConstraints(3, 0, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
        add(statusLabel, gridBagConstraints);

        setGridBagConstraints(3, 1, 2, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(statusCombo, gridBagConstraints);

        setGridBagConstraints(4, 0, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.LINE_START);
        add(uSCitizenLabel, gridBagConstraints);

        setGridBagConstraints(4, 1, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(uSCitizenCheckBox, gridBagConstraints);

        setGridBagConstraints(5, 0, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(taxIdLabel, gridBagConstraints);

        setGridBagConstraints(5, 1, 2, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(taxIdField, gridBagConstraints);

        setGridBagConstraints(6, 0, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(genderLabel, gridBagConstraints);

        setGridBagConstraints(6, 1, 2, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(radioButtonPanel, gridBagConstraints);

        setGridBagConstraints(7, 0, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
        add(deleteButton, gridBagConstraints);

        setGridBagConstraints(7, 1, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.CENTER);
        add(updateButton, gridBagConstraints);

        setGridBagConstraints(7, 2, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_END);
        add(addButton, gridBagConstraints);

        setGridBagConstraints(8, 0, 2, 0, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.PAGE_END);
        add(paddingPanel, gridBagConstraints);
    }

    private void setGridBagConstraints(int row, int column, int columnSpan, int insetValue, int columnWeight, int rowWeight, int fill, int anchor) {
        gridBagConstraints.gridx = column;
        gridBagConstraints.gridy = row;
        gridBagConstraints.gridwidth = columnSpan;
        gridBagConstraints.insets = new Insets(insetValue, insetValue, insetValue, insetValue);
        gridBagConstraints.weightx = columnWeight;
        gridBagConstraints.weighty = rowWeight;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
    }
}