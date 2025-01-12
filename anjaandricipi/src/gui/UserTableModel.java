package gui;

import models.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private List<User> users;
    private final String[] columnNames = { "ID", "Username", "First Name", "Last Name", "Salary", "Bonus", "Role", "Report" };

    public UserTableModel() {
        this.users = new ArrayList<>();
    }

    public UserTableModel(List<User> users) {
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }

    // Method to get User at a specific row index
    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getId();
            case 1: return user.getUsername();
            case 2: return user.getFirstName();
            case 3: return user.getLastName();
            case 4: return user.getSalary();
            case 5: return user.getBonus();
            case 6: return user.getRole();
            case 7: return user.getReport(); // Assuming User has a getReport() method
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}