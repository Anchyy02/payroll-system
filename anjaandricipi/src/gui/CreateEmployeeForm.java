package gui;

import dao.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CreateEmployeeForm extends JDialog{
    private JPanel panel;
    private JButton createButton;
    private JTextField firstNameField;
    private JComboBox rolecomboBox;
    private JTextField bonusField;
    private JTextField salaryField;
    private JTextField lastNameField;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel salaryLabel;
    private JLabel bonusLabel;
    private JLabel roleLabel;

    private JFrame parentFrame;

    public CreateEmployeeForm(JFrame parentFrame, boolean isModal) {
        super(parentFrame, isModal);
        this.parentFrame = parentFrame;

        setTitle("Create New Employee");
        setSize(300, 300);
        setLocationRelativeTo(parentFrame);

        panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel);

        panel.add(firstNameLabel);
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(lastNameLabel);
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(salaryLabel);
        salaryField = new JTextField();
        panel.add(salaryField);

        panel.add(bonusLabel);
        bonusField = new JTextField();
        panel.add(bonusField);

        panel.add(roleLabel);
        rolecomboBox = new JComboBox<>(new String[]{"employee", "manager"});
        panel.add(rolecomboBox);

        createButton = new JButton("Create");
        panel.add(createButton);

        createButton.addActionListener(e -> createEmployee());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createEmployee() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String salaryText = salaryField.getText();
        String bonusText = bonusField.getText();
        String role = (String) rolecomboBox.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty() || salaryText.isEmpty() || bonusText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double salary = Double.parseDouble(salaryText);
            double bonus = Double.parseDouble(bonusText);

            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setUsername(firstName.toLowerCase() + "." + lastName.toLowerCase()); // Example username generation
            user.setPassword("password"); // Default password, consider generating a random one
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setSalary(salary);
            user.setBonus(bonus);
            user.setRole(role);

            userDAO.createUser(user);

            if (parentFrame instanceof SuperAdminFrame) {
                ((SuperAdminFrame) parentFrame).reloadUsers();
            } else if (parentFrame instanceof ManagerFrame) {
                ((ManagerFrame) parentFrame).loadUsers();
            }

            JOptionPane.showMessageDialog(this, "Employee created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error creating employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
