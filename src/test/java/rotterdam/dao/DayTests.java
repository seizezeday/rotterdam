package rotterdam.dao;

import com.rotterdam.model.dao.DayDao;
import com.rotterdam.model.dao.WeekDao;
import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.Week;
import com.rotterdam.tools.json.JsonCommands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 17.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class DayTests {

    @Inject
    DayDao dayDao;

    @Inject
    WeekDao weekDao;

    @Test
    @Transactional
    //@Rollback(false)
    public void insertTest() throws ParseException {
        Week week = weekDao.selectByStartDateAndUser(new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN).parse("2015-01-12"), 5);
        Day day = new Day();
        day.setDate(new Date());
        day.setWeek(week);
        dayDao.insert(day);
    }
}
