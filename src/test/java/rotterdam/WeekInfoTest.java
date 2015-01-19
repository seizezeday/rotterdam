package rotterdam;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by root on 18.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class WeekInfoTest {
    //{"Monday":[
    //          {"date":"2015-01-05"},
    //           {"startWorkingTime":"15:00","endWorkingTime":"16:10","restTime":"25"},
    //           {"startWorkingTime":"19:00","endWorkingTime":"20:00","restTime":"10"}],
    // "Tuesday":[
    //           {"date":"19.01.20152015-01-06"},
    //           {"startWorkingTime":"","endWorkingTime":"","restTime":""}],
    // "Wednesday":[
    //           {"date":"26.01.20152015-01-07"},
    //           {"startWorkingTime":"16:00","endWorkingTime":"18:00","restTime":"20"}],
    // "Thursday":[
    //           {"date":"2015-01-08"},
    //           {"startWorkingTime":"","endWorkingTime":"","restTime":"","rideType":"Work"}],
    // "Friday":[
    //           {"date":"2015-01-09"},
    //           {"startWorkingTime":"","endWorkingTime":"","restTime":"","rideType":"Work"}],
    // "Saturday":[
    //           {"date":"2015-01-10"},
    //           {"startWorkingTime":"","endWorkingTime":"","restTime":"","rideType":"Work"}],
    // "Sunday":[
    //           {"date":"2015-01-11"},
    //           {"startWorkingTime":"","endWorkingTime":"","restTime":"","rideType":"Work"}]}
}
