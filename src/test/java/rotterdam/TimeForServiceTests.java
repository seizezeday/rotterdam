package rotterdam;

import com.rotterdam.dto.TimeForDto;
import com.rotterdam.service.TimeForService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Month;

/**
 * Created by root on 22.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class TimeForServiceTests {

    @Inject
    TimeForService timeForService;

    @Test
    @Rollback(true)
    public void basicCalculations(){
        TimeForDto timeForTimeOfPrevPeriod = timeForService.getTimeForOfPrevPeriod(LocalDate.of(2015, Month.FEBRUARY, 10), 5);
        System.out.println(timeForTimeOfPrevPeriod);
    }
}
