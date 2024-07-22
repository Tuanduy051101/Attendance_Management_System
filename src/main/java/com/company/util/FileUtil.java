package com.company.util;

import com.company.model.Attendance;
import com.company.service.AttendanceService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    public static void readAttendanceFromFile(String filePath, AttendanceService attendanceService) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Attendance attendance = new Attendance();
                attendance.setStudentId(Integer.parseInt(data[0]));
                attendance.setUserId(Integer.parseInt(data[1]));
                attendance.setDateAttended(java.sql.Date.valueOf(data[2]));
                attendance.setStatus(data[3]);
                attendanceService.insertAttendance(attendance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
