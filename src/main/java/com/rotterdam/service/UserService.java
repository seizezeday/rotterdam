package com.rotterdam.service;

import com.paypal.core.rest.PayPalRESTException;
import com.rotterdam.dto.PaymentResultDto;
import com.rotterdam.dto.UserDto;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.User;
import com.rotterdam.model.entity.UserRole;
import com.rotterdam.service.payment.PaymentService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.SecuritySettings;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vasax32 on 21.01.15.
 */
@Named
public class UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private PaymentService paymentService;


    @Transactional
    public String save(UserDto userDto, UserRole userRole, String domain) throws PayPalRESTException {
        if (checkPassword(userDto.pass, userDto.passconfirm) && checkEmail(userDto.email) && userRole != null) {
            PaymentResultDto paymentResultDto = paymentService.doPayment(domain);
            User user = convertToUser(userDto);
            user.setPassword(SecuritySettings.code(userDto.pass));
            user.setRole(userRole);
            user.setRegNum(userDto.regNum);
            user.setPaymentId(paymentResultDto.paymentId);
            userDao.insert(user);
            return paymentResultDto.approvalUrl;
        } else {
            return null;
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

    @Transactional
    public User getByEmailAndPass(String login, String pass){
        return userDao
                .selectByEmailAndPass(login,
                        SecuritySettings.code(pass));
    }

    public boolean isUserPayed(User user){
        Date lastPaymentDate = user.getLastPaymentDate();
        if(lastPaymentDate == null)
            return false;
        return DateTools.isAfter(DateTools.getDatePlusMonth(lastPaymentDate, 1), new Date());
    }

    @Transactional
    public String doPaymentLogin(User user, String domain) throws PayPalRESTException {
        PaymentResultDto paymentResultDto = paymentService.doPayment(domain);
        user.setPaymentId(paymentResultDto.paymentId);
        userDao.update(user);
        return paymentResultDto.approvalUrl;
    }
}
