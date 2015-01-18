package rotterdam.dao;

import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by root on 17.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class BasicTests {

    @Inject
    UserDao userDao;

    @Test
    public void getAllUsers(){
        List<User> users = userDao.selectAll();
        for (User user : users)
            System.out.println(user);
    }

    @Test
    public void getUserByEmail(){
        User user = userDao.selectByEmail("root");
        System.out.println(user);
    }
}
