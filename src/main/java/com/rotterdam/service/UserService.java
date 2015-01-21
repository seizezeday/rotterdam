package com.rotterdam.service;

import com.rotterdam.dto.UserDto;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.dao.UserRoleDao;
import com.rotterdam.model.entity.User;
import com.rotterdam.model.entity.UserRole;
import com.rotterdam.tools.SecuritySettings;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 21.01.15.
 */
@Named
public class UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private UserRoleDao userRoleDao;

    @Transactional
    public boolean save(UserDto userDto, int roleId){
        UserRole userRole = userRoleDao.selectById(roleId);
        if (checkPassword(userDto.pass, userDto.passconfirm) && checkEmail(userDto.email) && userRole != null) {
            User user = convertToUser(userDto);
            user.setPassword(SecuritySettings.code(userDto.pass));
            user.setRole(userRole);
            userDao.insert(user);
            return true;
        } else {
            return false;
        }
    }

    private User convertToUser(UserDto userDto){
        User user = new User();
        user.setFirstname(userDto.Name);
        user.setSurname(userDto.LastName);
        user.setEmail(userDto.email);
        return user;
    }

    private boolean checkPassword(String password, String confirmPassword) {
        if (password == null || password.equals("")) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            return false;
        }
        return true;
    }

    private boolean checkEmail(String email) {
        if (email == null || email.equals("")) {
            return false;
        }
        Pattern emailPattern = Pattern.compile("(?<email>[\\w.]+@[\\w.]+)");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.find()) {
            return false;
        }
        User user = userDao.selectByEmail(email);
        return user == null;
    }
}
