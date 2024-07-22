package com.company.dao;

import com.company.attendance.config.DatabaseConnection;
import com.company.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDao {
    // Phương thức lấy thông tin vai trò dựa trên ID người dùng
    public Role getRoleByUserId(int userId) {
        String query = "SELECT r.* FROM Roles r JOIN UserRoles ur ON r.role_id = ur.role_id WHERE ur.user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("role_id"));
                role.setRoleName(rs.getString("role_name"));
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
