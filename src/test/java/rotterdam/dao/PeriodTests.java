package rotterdam.dao;

import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.PeriodService;
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
    PeriodService periodService;
    @Inject
    UserDao userDao;

    @Test
    public void checkPeriodCheck(){
        User user = userDao.selectByEmail("superuser@mail.com");
        periodService.makePeriodCheck(user);
    }
}
