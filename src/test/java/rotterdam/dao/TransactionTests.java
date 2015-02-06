package rotterdam.dao;

import com.rotterdam.model.dao.DayDao;
import com.rotterdam.model.entity.Day;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by root on 06.02.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class TransactionTests {
    @Inject
    private DayDao dayDao;

    @Test
    @Transactional
    public void basicTest() {
        Day day = dayDao.selectById(78);
        System.out.println("===========>" + day);
        System.out.println("===========>" + day.getDeclarations());
        System.out.println("===========>" + day.getWeek());
    }
}
