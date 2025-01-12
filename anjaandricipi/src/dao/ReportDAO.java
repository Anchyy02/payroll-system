package dao;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {

    public void updateReportByUserId(int userId, String content) throws SQLException {
        deleteReportByUserId(userId); // Delete the existing report before adding a new one
        String query = "INSERT INTO reports (user_id, content) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, content);
            stmt.executeUpdate();
        }
    }

    public String getReportByUserId(int userId) throws SQLException {
        String query = "SELECT content FROM reports WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("content");
                }
            }
        }
        return "No report available.";
    }

    private void deleteReportByUserId(int userId) throws SQLException {
        String query = "DELETE FROM reports WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
}