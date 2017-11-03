package com.MainApp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientHome extends JFrame {
    int clientID;
    String Email;
    private JPanel ClientHomeForm;
    private JTabbedPane clientHome;
    private JPanel profilePanel;
    private JPanel searchResults;
    private JPanel managePanel;
    private JPanel searchProperty;
    private JLabel welcomeLabel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField contactField;
    private JPasswordField currentPassword;
    private JPasswordField newPassword;
    private JPasswordField confirmNewPassword;
    private JButton updateButton;
    private JButton resetButton;
    private JLabel resultMessage;

    //Constructor with Form Listener
    public ClientHome() {
        clientHome.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = clientHome.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        loadProfile();
                        break;
                    case 1:
                        //loadSearchPage();
                        break;
                    case 2:
                        //loadSearchResult();
                        break;
                    case 3:
                        //loadManageProperty();
                        break;
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProfile();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfile();
            }
        });
    }

    //User Defined Constructor to set clientID and Email of the logged in User
    public ClientHome(int ID, String uname) {
        this();
        clientID = ID;
        Email = uname;
        welcomeLabel.setText("Welcome User " + Email);
    }

    void loadProfile() {
        try {
            DatabaseConnect databaseConnect = new DatabaseConnect();
            System.out.println(Email);
            ResultSet rs = databaseConnect.selectQuery("select * from tbl_user where user_email = '" + Email + "';");
            if (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(5));
                nameField.setText(rs.getString(2));
                addressField.setText(rs.getString(3));
                contactField.setText(rs.getString(5));
                currentPassword.setText("");
                newPassword.setText("");
                confirmNewPassword.setText("");
            }
            rs.close();
            databaseConnect.close();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
            resultMessage.setText("Error: Unable to Establish Database Connection");
        }
    }

    void updateProfile() {
        String newUserName = nameField.getText();
        String newAddress = addressField.getText();
        String newContact = contactField.getText();
        String currPwd = String.valueOf(currentPassword.getPassword());
        String newPwd = String.valueOf(newPassword.getPassword());
        String confirmNewPwd = String.valueOf(confirmNewPassword.getPassword());
        try {
            DatabaseConnect databaseConnect = new DatabaseConnect();
            ResultSet rs = databaseConnect.selectQuery("select * from tbl_user where user_email = '" + Email + "';");
            if (rs.next()) {
                /*String dbUsername = rs.getString(2);
                String dbAddress = rs.getString(2);
                String dbContact = rs.getString(5);*/
                String dbPwd = rs.getString(4);
                if (currPwd.equals(dbPwd)) {
                    if (!newPwd.equals("") && !confirmNewPwd.equals("")) {
                        if (newPwd.equals(confirmNewPwd))
                            updateProfileQuery(newPwd);
                        else
                            resultMessage.setText("New Password does not match with Confirm New Password");
                    }
                    if (!newAddress.equals("") && !newUserName.equals("") && !newContact.equals(""))
                        updateProfileQuery(newUserName, newAddress, newContact);
                    else
                        resultMessage.setText(resultMessage.getText() + "\n" + "All fields not populated");
                } else {
                    resultMessage.setText("Error: Incorrect Current Password");
                    loadProfile();
                }
            }
            loadProfile();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
            resultMessage.setText("Error: Unable to Establish Database Connection");
        }
    }

    void updateProfileQuery(String password) {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        databaseConnect.updateQuery("update tbl_user set user_password = '" + password + "' where user_email = '" + Email + "';");
        databaseConnect.close();
    }

    void updateProfileQuery(String uname, String address, String contact) {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        databaseConnect.updateQuery("update tbl_user set user_name = '" + uname + "', user_address = '" + address + "', user_contact_number = '" + contact + "' where user_email = '" + Email + "';");
        databaseConnect.close();
    }

    Container getForm() {
        return ClientHomeForm;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        ClientHomeForm = new JPanel();
        ClientHomeForm.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        clientHome = new JTabbedPane();
        ClientHomeForm.add(clientHome, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        profilePanel = new JPanel();
        profilePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 12, new Insets(0, 0, 0, 0), -1, -1));
        clientHome.addTab("My Profile", profilePanel);
        final JLabel label1 = new JLabel();
        label1.setText("Name");
        profilePanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        profilePanel.add(nameField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Address");
        profilePanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addressField = new JTextField();
        profilePanel.add(addressField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Contact");
        profilePanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contactField = new JTextField();
        profilePanel.add(contactField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Change Password");
        profilePanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Current Password");
        profilePanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPassword = new JPasswordField();
        profilePanel.add(currentPassword, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("New Password");
        profilePanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newPassword = new JPasswordField();
        newPassword.setText("");
        profilePanel.add(newPassword, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Confirm New Password");
        profilePanel.add(label7, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmNewPassword = new JPasswordField();
        profilePanel.add(confirmNewPassword, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Update");
        profilePanel.add(updateButton, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resetButton = new JButton();
        resetButton.setText("Reset");
        profilePanel.add(resetButton, new com.intellij.uiDesigner.core.GridConstraints(7, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultMessage = new JLabel();
        resultMessage.setText("");
        profilePanel.add(resultMessage, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchProperty = new JPanel();
        searchProperty.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        clientHome.addTab("Search Property", searchProperty);
        searchResults = new JPanel();
        searchResults.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        clientHome.addTab("Search Results", searchResults);
        managePanel = new JPanel();
        managePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        clientHome.addTab("Manage Property", managePanel);
        welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome user");
        ClientHomeForm.add(welcomeLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ClientHomeForm;
    }
}
