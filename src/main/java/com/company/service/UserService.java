package com.company.service;

import com.company.dao.PermissionDao;
import com.company.dao.RoleDao;
import com.company.dao.UserDao;
import com.company.model.Permission;
import com.company.model.Role;
import com.company.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();
    private RoleDao roleDao = new RoleDao();
    private PermissionDao permissionDao = new PermissionDao();

    public boolean authenticate(String username, String password) {
        User user = userDao.getUserByUsernameAndPassword(username, password);
        return user != null;
    }

    public boolean hasPermission(int userId, String permissionName) {
        Role role = roleDao.getRoleByUserId(userId);
        List<Permission> permissions = permissionDao.getPermissionsByRoleId(role.getRoleId());
        return permissions.stream().anyMatch(p -> p.getPermissionName().equals(permissionName));
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username, password);
    }
}
