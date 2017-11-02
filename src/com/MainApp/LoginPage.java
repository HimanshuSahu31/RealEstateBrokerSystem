package com.MainApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginPage {
    private JButton loginButton;
    private JTextField unameField;
    private JPasswordField pwordField;
    private JLabel uname_label;
    private JLabel pword_label;
    private JPanel LoginForm;
    private JLabel resultMessage;

    public LoginPage() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = unameField.getText();
                String password = String.valueOf(pwordField.getPassword());
                //System.out.println(username + " abc " + password);
                if (!username.equals("") && !password.equals("")) {
                    System.out.println(username + " " + password);
                    try {
                        DatabaseConnect connectionDB = new DatabaseConnect();
                        connectionDB.setAutoCommit(false);
                        Statement stmt = connectionDB.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from tbl_user where user_name = '" + username + "' and user_password = '" + password + "';");
                        if (rs.next()) {
                            resultMessage.setText("Authentication Successful");
                            int getUserID = rs.getInt(1);
                            rs = stmt.executeQuery("select owner_id from tbl_owner where user_id = '" + getUserID + "';");
                            if (rs.next()) {
                                int getOwnerID = rs.getInt(1);
                                OwnerHome ownerHome = new OwnerHome(getOwnerID);
                                ownerHome.setVisible(true);
                            } else {
                                rs = stmt.executeQuery("select client_id from tbl_client where user_id = '" + getUserID + "';");
                                if (rs.next()) {
                                    int getClientID = rs.getInt(1);
                                } else {
                                    resultMessage.setText("User is neither an Owner or Client");
                                }
                            }
                        } else {
                            resultMessage.setText("Incorrect Username or Password");
                        }
                        rs.close();
                        connectionDB.close();
                        stmt.close();
                    } catch (SQLException exp) {
                        System.out.println(exp.getMessage());
                    }
                } else {
                    String msg = "One or Both of the fields Username, Password is empty";
                    System.out.println(msg);
                    //JOptionPane.showMessageDialog(null, msg);
                    resultMessage.setText(msg);
                    unameField.setText("");
                    pwordField.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Page: Real Estate Broker System");
        frame.setContentPane(new LoginPage().LoginForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        LoginForm = new JPanel();
        LoginForm.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        LoginForm.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        uname_label = new JLabel();
        uname_label.setText("Username:");
        LoginForm.add(uname_label, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setText("Login");
        LoginForm.add(loginButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        unameField = new JTextField();
        unameField.setToolTipText("Enter you username");
        LoginForm.add(unameField, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pwordField = new JPasswordField();
        pwordField.setToolTipText("Enter Your Password");
        LoginForm.add(pwordField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        resultMessage = new JLabel();
        resultMessage.setText("");
        LoginForm.add(resultMessage, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pword_label = new JLabel();
        pword_label.setText("Password:");
        LoginForm.add(pword_label, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return LoginForm;
    }
}
