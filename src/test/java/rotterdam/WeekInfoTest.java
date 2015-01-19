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

//{"Monday":[{"date":"2015-01-12"},{"startWorkingTime":"09:00","endWorkingTime":"10:00","restTime":"12","dayType":null},
// {"startWorkingTime":"09:00","endWorkingTime":"10:00","restTime":"56"}],
// "Tuesday":[{"date":"2015-01-13"},{"startWorkingTime":"09:00","endWorkingTime":"00:34","restTime":"","dayType":null}],
// "Wednesday":[{"date":"2015-01-14"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":null}],
// "Thursday":[{"date":"2015-01-15"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":null}],
// "Friday":[{"date":"2015-01-16"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":null}],
// "Saturday":[{"date":"2015-01-17"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],
// "Sunday":[{"date":"2015-01-18"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}]}


//////////////////////////////////////////////


//{"Monday":
// [{"date":"2015-01-12"},{"startWorkingTime":"10:00","endWorkingTime":"00:51","restTime":"12","dayType":"1"},
// {"startWorkingTime":"10:00","endWorkingTime":"00:51","restTime":"25","dayType":"1"}],"
// Tuesday":[{"date":"2015-01-13"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],
// "Wednesday":[{"date":"2015-01-14"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],"Thursday":[{"date":"2015-01-15"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],"Friday":[{"date":"2015-01-16"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],"Saturday":[{"date":"2015-01-17"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],"Sunday":[{"date":"2015-01-18"},{"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}]}