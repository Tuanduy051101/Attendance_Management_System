package com.company.dao;

import com.company.attendance.config.DatabaseConnection;
import com.company.model.Attendance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    // Phương thức chèn thông tin điểm danh vào cơ sở dữ liệu
    public void insertAttendance(Attendance attendance) {
        String query = "INSERT INTO Attendance (student_id, user_id, date_attended, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, attendance.getStudentId());
            stmt.setInt(2, attendance.getUserId());
            stmt.setDate(3, attendance.getDateAttended());
            stmt.setString(4, attendance.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức lấy danh sách điểm danh hàng ngày
    public List<Attendance> getDailyAttendance() {
        String query = "SELECT * FROM Attendance WHERE date_attended = CURRENT_DATE";
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setUserId(rs.getInt("user_id"));
                attendance.setDateAttended(rs.getDate("date_attended"));
                attendance.setStatus(rs.getString("status"));
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // Phương thức lấy danh sách điểm danh tổng thể
    public List<Attendance> getOverallAttendance() {
        String query = "SELECT student_id, COUNT(*) AS total_days, SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) AS present_days FROM Attendance GROUP BY student_id";
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setTotalDays(rs.getInt("total_days"));
                attendance.setPresentDays(rs.getInt("present_days"));
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // Phương thức lấy danh sách học viên có mặt
    public List<Attendance> getPresentStudents() {
        String query = "SELECT * FROM Attendance WHERE status = 'Present'";
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setUserId(rs.getInt("user_id"));
                attendance.setDateAttended(rs.getDate("date_attended"));
                attendance.setStatus(rs.getString("status"));
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // Phương thức lấy danh sách học viên vắng mặt
    public List<Attendance> getAbsentStudents() {
        String query = "SELECT * FROM Attendance WHERE status = 'Absent'";
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setAttendanceId(rs.getInt("attendance_id"));
                attendance.setStudentId(rs.getInt("student_id"));
                attendance.setUserId(rs.getInt("user_id"));
                attendance.setDateAttended(rs.getDate("date_attended"));
                attendance.setStatus(rs.getString("status"));
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }
}
