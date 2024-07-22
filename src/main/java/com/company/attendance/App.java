package com.company.attendance;

import com.company.service.AttendanceService;
import com.company.service.UserService;
import com.company.util.FileUtil;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        AttendanceService attendanceService = new AttendanceService();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.authenticate(username, password)) {
            System.out.println("Login successful!");
            System.out.println("1. Load attendance data from file");
            System.out.println("2. Generate daily report");
            System.out.println("3. Generate overall report");
            System.out.println("4. Generate present students report");
            System.out.println("5. Generate absent students report");
            int choice = scanner.nextInt();
            scanner.nextLine();

            int userId = userService.getUserByUsernameAndPassword(username, password).getUserId();

            switch (choice) {
                case 1:
                    if (userService.hasPermission(userId, "MARK_ATTENDANCE") || userService.hasPermission(userId, "FULL_ACCESS")) {
                        System.out.print("Enter file path: ");
                        String filePath = scanner.nextLine();
                        FileUtil.readAttendanceFromFile(filePath, attendanceService);
                    } else {
                        System.out.println("You do not have permission to mark attendance.");
                    }
                    break;
                case 2:
                    if (userService.hasPermission(userId, "VIEW_ATTENDANCE") || userService.hasPermission(userId, "FULL_ACCESS")) {
                        attendanceService.getDailyAttendance().forEach(System.out::println);
                    } else {
                        System.out.println("You do not have permission to view attendance.");
                    }
                    break;
                case 3:
                    if (userService.hasPermission(userId, "VIEW_ATTENDANCE") || userService.hasPermission(userId, "FULL_ACCESS")) {
                        attendanceService.getOverallAttendance().forEach(System.out::println);
                    } else {
                        System.out.println("You do not have permission to view attendance.");
                    }
                    break;
                case 4:
                    if (userService.hasPermission(userId, "VIEW_ATTENDANCE") || userService.hasPermission(userId, "FULL_ACCESS")) {
                        attendanceService.getPresentStudents().forEach(System.out::println);
                    } else {
                        System.out.println("You do not have permission to view attendance.");
                    }
                    break;
                case 5:
                    if (userService.hasPermission(userId, "VIEW_ATTENDANCE") || userService.hasPermission(userId, "FULL_ACCESS")) {
                        attendanceService.getAbsentStudents().forEach(System.out::println);
                    } else {
                        System.out.println("You do not have permission to view attendance.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Login failed!");
        }
    }
}
