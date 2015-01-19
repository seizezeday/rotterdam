package rotterdam.dao;

import com.rotterdam.controllers.UserInfo;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by root on 17.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class PeriodTests {

    @Inject
    UserInfo userInfo;

    @Inject
    UserDao userDao;

    @Test
    public void checkPeriodCheck(){
        User user = userDao.selectByEmail("superuser@mail.com");
        userInfo.makePeriodCheck(user);
    }
}
