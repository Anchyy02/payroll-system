package gui;

import dao.UserDAO;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SuperAdminFrame extends JFrame{
    private JPanel panel;
    private JButton createUserButton;
    private JButton editUserButton;
    private JButton deleteUserButton;
    private JTable userTable;
    private JButton logoutButton;
    private JScrollPane scrollPane;

    private UserDAO userDAO;
    private UserTableModel userTableModel;

    public SuperAdminFrame() {
        setTitle("Super Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userDAO = new UserDAO();

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

        createUserButton = new JButton("Create User");
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
        editUserButton = new JButton("Edit User");
        editUserButton.addActionListener(e -> editUser());
        deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(e -> deleteUser());
        buttonPanel.add(editUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(logoutButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        //panel.add(logoutButton, BorderLayout.WEST);

        loadUsers();
    }

    public void loadUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            userTableModel.setUsers(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.");
            return;
        }

        User selectedUser = userTableModel.getUserAt(selectedRow);
        new EditUserForm(this, selectedUser).setVisible(true);
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            User selectedUser = userTableModel.getUserAt(selectedRow);
            try {
                userDAO.deleteUser(selectedUser.getId());
                loadUsers();
                JOptionPane.showMessageDialog(this, "User deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
            }
        }
    }

    public void reloadUsers() {
        loadUsers();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SuperAdminFrame().setVisible(true));
    }

}
