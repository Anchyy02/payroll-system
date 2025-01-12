package dao;

import models.User;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        }
        return null;
    }

    public void createUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, first_name, last_name, salary, bonus, role, content) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setDouble(5, user.getSalary());
            stmt.setDouble(6, user.getBonus());
            stmt.setString(7, user.getRole());
            stmt.setString(8, user.getContent());
            stmt.executeUpdate();
        }
    }

    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, salary = ?, bonus = ?, role = ?, content = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setDouble(5, user.getSalary());
            stmt.setDouble(6, user.getBonus());
            stmt.setString(7, user.getRole());
            stmt.setString(8, user.getContent());
            stmt.setInt(9, user.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setSalary(rs.getDouble("salary"));
        user.setBonus(rs.getDouble("bonus"));
        user.setRole(rs.getString("role"));
        user.setContent(rs.getString("content"));
        return user;
    }
}