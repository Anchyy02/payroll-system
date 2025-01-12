package gui;

import dao.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditUserForm extends JDialog{
    private JPanel panel;
    private JButton saveButton;
    private JTextField usernameField;
    private JTextField firstNameField;
    private JPasswordField passwordField;
    private JComboBox roleComboBox;
    private JTextField bonusField;
    private JTextField salaryField;
    private JTextField lastNameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel salaryLabel;
    private JLabel bonusLabel;
    private JLabel roleLabel;

    private User user;

    public EditUserForm(JFrame parentFrame, User user) {
        super(parentFrame, true);
        this.user = user;

        setTitle("Edit User");
        setSize(400, 400);
        setLocationRelativeTo(parentFrame);

        panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel);

        panel.add(usernameLabel);
        usernameField = new JTextField(user.getUsername());
        panel.add(usernameField);

        panel.add(passwordLabel);
        passwordField = new JPasswordField(user.getPassword());
        panel.add(passwordField);

        panel.add(firstNameLabel);
        firstNameField = new JTextField(user.getFirstName());
        panel.add(firstNameField);

        panel.add(lastNameLabel);
        lastNameField = new JTextField(user.getLastName());
        panel.add(lastNameField);

        panel.add(salaryLabel);
        salaryField = new JTextField(String.valueOf(user.getSalary()));
        panel.add(salaryField);

        panel.add(bonusLabel);
        bonusField = new JTextField(String.valueOf(user.getBonus()));
        panel.add(bonusField);

        panel.add(roleLabel);
        roleComboBox = new JComboBox<>(new String[]{"employee", "manager", "superadmin"});
        roleComboBox.setSelectedItem(user.getRole());
        panel.add(roleComboBox);


        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveUser());
        panel.add(saveButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void saveUser() {
        user.setUsername(usernameField.getText());
        user.setPassword(new String(passwordField.getPassword()));
        user.setFirstName(firstNameField.getText());
        user.setLastName(lastNameField.getText());
        user.setSalary(Double.parseDouble(salaryField.getText()));
        user.setBonus(Double.parseDouble(bonusField.getText()));
        user.setRole((String) roleComboBox.getSelectedItem());

        try {
            UserDAO userDAO = new UserDAO();
            userDAO.updateUser(user);
            ((SuperAdminFrame) getParent()).reloadUsers();
            JOptionPane.showMessageDialog(this, "User updated successfully.");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating user: " + e.getMessage());
        }
    }

}
