package com.company.service;

import com.company.dao.AttendanceDao;
import com.company.model.Attendance;

import java.util.List;

public class AttendanceService {
    private AttendanceDao attendanceDao = new AttendanceDao();

    public void loadAttendanceFromFile(String filePath) {

    }

    public List<Attendance> getDailyAttendance() {
        return attendanceDao.getDailyAttendance();
    }

    public List<Attendance> getOverallAttendance() {
        return attendanceDao.getOverallAttendance();
    }

    public List<Attendance> getPresentStudents() {
        return attendanceDao.getPresentStudents();
    }

    public List<Attendance> getAbsentStudents() {
        return attendanceDao.getAbsentStudents();
    }

    public void insertAttendance(Attendance attendance) {
        attendanceDao.insertAttendance(attendance);
    }
}
