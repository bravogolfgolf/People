package ui;

import domain.FormEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;

class FormPanel extends JPanel {

    private final FormPanelListener formPanelListener;
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
    final JButton okButton = new JButton("OK");
    private final JPanel paddingPanel = new JPanel();
    private final GridBagConstraints gridBagConstraints = new GridBagConstraints();

    private Boolean uSCitizen;
    private String taxId;

    FormPanel(FormPanelListener formPanelListener) {
        this.formPanelListener = formPanelListener;
        setupComponents();
        addListeners();
        addComponentsToFormPanel();
    }

    private void setupComponents() {
        setupFormPanel();
        setupAgeList();
        setupStatusCombo();
        setupTaxIdLabel();
        setupTaxIdField();
        setupRadioButtonPanel();
    }

    private void setupFormPanel() {
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

    private void setupTaxIdLabel() {
        taxIdLabel.setEnabled(false);
        taxIdLabel.setVisible(false);
    }

    private void setupTaxIdField() {

        try {
            MaskFormatter ssnFormatter = new MaskFormatter("###-##-####");
            ssnFormatter.setPlaceholderCharacter('0');
            taxIdField = new JFormattedTextField(ssnFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        taxIdField.setEnabled(false);
        taxIdField.setVisible(false);
    }

    private void setupRadioButtonPanel() {
        maleRadioButton.setActionCommand("Male");
        femaleRadioButton.setActionCommand("Female");
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
        radioButtonPanel.add(maleRadioButton);
        radioButtonPanel.add(femaleRadioButton);
    }

    private void addListeners() {
        addUsCitizenCheckBoxListener();
        addOkButtonListener();
    }

    private void addUsCitizenCheckBoxListener() {
        uSCitizenCheckBox.addActionListener(e -> {
            taxIdLabel.setEnabled(uSCitizenCheckBox.isSelected());
            taxIdLabel.setVisible(uSCitizenCheckBox.isSelected());
            taxIdField.setEnabled(uSCitizenCheckBox.isSelected());
            taxIdField.setVisible(uSCitizenCheckBox.isSelected());
        });
    }

    private void addOkButtonListener() {
        okButton.addActionListener((ActionEvent e) -> {
            String fullName = nameField.getText();
            String occupation = occupationField.getText();
            int ageCategory = ageList.getSelectedIndex();
            int employmentStatus = statusCombo.getSelectedIndex();
            if (uSCitizenCheckBox.isSelected()) {
                uSCitizen = true;
                taxId = taxIdField.getValue() != null ? (String) taxIdField.getValue() : "000-00-0000";
            } else {
                uSCitizen = false;
                taxId = "000-00-0000";
            }
            String gender = genderButtonGroup.getSelection().getActionCommand();
            formPanelListener.eventEmitted(new FormEvent(e, fullName, occupation, ageCategory, employmentStatus, uSCitizen, taxId, gender));
        });
    }

    private void addComponentsToFormPanel() {

        setGridBagConstraints(0, 0, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(nameLabel, gridBagConstraints);

        setGridBagConstraints(1, 0, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(nameField, gridBagConstraints);

        setGridBagConstraints(0, 1, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(occupationLabel, gridBagConstraints);

        setGridBagConstraints(1, 1, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(occupationField, gridBagConstraints);

        setGridBagConstraints(0, 2, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
        gridBagConstraints.insets = new Insets(3, 0, 0, 0);
        add(ageLabel, gridBagConstraints);

        setGridBagConstraints(1, 2, 1, 3, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(ageList, gridBagConstraints);

        setGridBagConstraints(0, 3, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_START);
        add(statusLabel, gridBagConstraints);

        setGridBagConstraints(1, 3, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(statusCombo, gridBagConstraints);

        setGridBagConstraints(0, 4, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.LINE_START);
        add(uSCitizenLabel, gridBagConstraints);

        setGridBagConstraints(1, 4, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(uSCitizenCheckBox, gridBagConstraints);

        setGridBagConstraints(0, 5, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(taxIdLabel, gridBagConstraints);

        setGridBagConstraints(1, 5, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(taxIdField, gridBagConstraints);

        setGridBagConstraints(0, 6, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(genderLabel, gridBagConstraints);

        setGridBagConstraints(1, 6, 1, 0, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.LINE_START);
        add(radioButtonPanel, gridBagConstraints);

        setGridBagConstraints(1, 7, 1, 0, 0, 0, GridBagConstraints.NONE, GridBagConstraints.FIRST_LINE_END);
        add(okButton, gridBagConstraints);

        setGridBagConstraints(0, 8, 2, 0, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.PAGE_END);
        add(paddingPanel, gridBagConstraints);
    }

    private void setGridBagConstraints(int column, int row, int columnSpan, int insetValue, int columnWeight, int rowWeight, int fill, int anchor) {
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

