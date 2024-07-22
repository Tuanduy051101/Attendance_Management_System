package com.company.dao;

import com.company.attendance.config.DatabaseConnection;
import com.company.model.Permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionDao {
    // Phương thức lấy danh sách quyền dựa trên ID vai trò
    public List<Permission> getPermissionsByRoleId(int roleId) {
        String query = "SELECT p.* FROM Permissions p JOIN RolePermissions rp ON p.permission_id = rp.permission_id WHERE rp.role_id = ?";
        List<Permission> permissions = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Permission permission = new Permission();
                permission.setPermissionId(rs.getInt("permission_id"));
                permission.setPermissionName(rs.getString("permission_name"));
                permissions.add(permission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }
}
