package gui;

import dao.ReportDAO;
import dao.UserDAO;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EmployeeFrame extends JFrame{
    private JPanel panel;
    private JButton logoutButton;
    private JTextArea personalDataArea;
    private JLabel welcomeLabel;

    private User user;
    private UserDAO userDAO;
    private ReportDAO reportDAO;

    public EmployeeFrame(User user) {
        this.user = user;
        setTitle("Employee Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userDAO = new UserDAO();
        reportDAO = new ReportDAO();

        // Set global UI properties
        UIManager.put("Button.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextArea.background", Color.LIGHT_GRAY);
        UIManager.put("TextArea.foreground", Color.BLACK);

        panel = new JPanel(new BorderLayout());
        add(panel);

        welcomeLabel = new JLabel("Welcome, " + user.getFirstName() + " " + user.getLastName(), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        personalDataArea.setEditable(false);
        panel.add(new JScrollPane(personalDataArea), BorderLayout.CENTER);

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        panel.add(logoutButton, BorderLayout.SOUTH);

        loadPersonalData(personalDataArea);
    }

    private void loadPersonalData(JTextArea personalDataArea) {
        try {
            User personalData = userDAO.getUserById(user.getId());
            String reportContent = reportDAO.getReportByUserId(user.getId());

            StringBuilder sb = new StringBuilder();
            sb.append("First Name: ").append(personalData.getFirstName()).append("\n");
            sb.append("Last Name: ").append(personalData.getLastName()).append("\n");
            sb.append("Salary: ").append(personalData.getSalary()).append("\n");
            sb.append("Bonus: ").append(personalData.getBonus()).append("\n");
            sb.append("Report: ").append(reportContent).append("\n");

            personalDataArea.setText(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading personal data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeFrame(new User()).setVisible(true));
    }

}
