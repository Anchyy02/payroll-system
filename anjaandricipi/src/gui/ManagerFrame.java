package gui;

import dao.ReportDAO;
import dao.UserDAO;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManagerFrame extends JFrame{
    private JPanel panel;
    private JButton logoutButton;
    private JButton createUserButton;
    private JTable userTable;
    private JScrollPane scrollPane;
    private JButton writeReportButton;
    private JButton updateSalaryButton;
    private JButton updateBonusButton;

    private User user;
    private UserDAO userDAO;
    private ReportDAO reportDAO;
    private UserTableModel userTableModel;

    public ManagerFrame(User user) {
        this.user = user;
        setTitle("Manager Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userDAO = new UserDAO();
        reportDAO = new ReportDAO();

        // Set global UI properties
        UIManager.put("Button.background", Color.LIGHT_GRAY);
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("Table.background", Color.LIGHT_GRAY);
        UIManager.put("Table.foreground", Color.BLACK);
        UIManager.put("Table.gridColor", Color.BLACK);
        UIManager.put("TableHeader.background", Color.GRAY);
        UIManager.put("TableHeader.foreground", Color.WHITE);

        panel = new JPanel(new BorderLayout());
        add(panel);


        createUserButton.addActionListener(e -> new CreateEmployeeForm(this, true).setVisible(true));
        panel.add(createUserButton, BorderLayout.NORTH);

        userTableModel = new UserTableModel();
        userTable = new JTable(userTableModel);
        scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Center align table headers
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = userTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

        JPanel buttonPanel = new JPanel();
        writeReportButton.addActionListener(e -> writeReport());
        updateSalaryButton.addActionListener(e -> updateSalary());
        updateBonusButton.addActionListener(e -> updateBonus());
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        buttonPanel.add(writeReportButton);
        buttonPanel.add(updateSalaryButton);
        buttonPanel.add(updateBonusButton);
        buttonPanel.add(logoutButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadUsers();
    }

    public void loadUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            for (User user : users) {
                String report = reportDAO.getReportByUserId(user.getId());
                user.setReport(report);
            }
            userTableModel.setUsers(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeReport() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to write a report.");
            return;
        }

        User selectedUser = userTableModel.getUserAt(selectedRow);
        String reportContent = JOptionPane.showInputDialog(this, "Enter report content:");
        if (reportContent != null && !reportContent.isEmpty()) {
            try {
                reportDAO.updateReportByUserId(selectedUser.getId(), reportContent);
                loadUsers(); // Reload users to refresh the table with updated reports
                JOptionPane.showMessageDialog(this, "Report written successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error writing report: " + e.getMessage());
            }
        }
    }

    private void updateSalary() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update salary.");
            return;
        }

        User selectedUser = userTableModel.getUserAt(selectedRow);
        String newSalaryText = JOptionPane.showInputDialog(this, "Enter new salary:");
        if (newSalaryText != null && !newSalaryText.isEmpty()) {
            try {
                double newSalary = Double.parseDouble(newSalaryText);
                selectedUser.setSalary(newSalary);
                userDAO.updateUser(selectedUser);
                loadUsers();
                JOptionPane.showMessageDialog(this, "Salary updated successfully.");
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating salary: " + e.getMessage());
            }
        }
    }

    private void updateBonus() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to update bonus.");
            return;
        }

        User selectedUser = userTableModel.getUserAt(selectedRow);
        String newBonusText = JOptionPane.showInputDialog(this, "Enter new bonus:");
        if (newBonusText != null && !newBonusText.isEmpty()) {
            try {
                double newBonus = Double.parseDouble(newBonusText);
                selectedUser.setBonus(newBonus);
                userDAO.updateUser(selectedUser);
                loadUsers();
                JOptionPane.showMessageDialog(this, "Bonus updated successfully.");
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating bonus: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManagerFrame(new User()).setVisible(true));
    }

}
