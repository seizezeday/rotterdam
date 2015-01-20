package com.rotterdam.controllers;

import com.rotterdam.dto.UserInfoDto;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.entity.Period;
import com.rotterdam.model.entity.PeriodType;
import com.rotterdam.model.entity.User;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.Date;

@Path("/")
@PermitAll
@Named
public class UserInfo {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private PeriodDao periodDao;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/home")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getInfo(@Context HttpServletRequest hsr ) throws JsonException, ParseException {
//        UserInfoDto jsonData = jsonCommands.getInitAfterLoginData(hsr);
        User user = jsonCommands.getUserFromRequest(hsr);

//        System.out.println(jsonData);

        //makePeriodCheck(jsonCommands.getUserFromRequest(hsr));

        if (user != null){
            UserInfoDto userInfoDto = UserInfoDto.formatForNow(user.getFirstname());
            return Response.ok(userInfoDto).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Transactional
    public void makePeriodCheck(User user){
        //now we need to deal with period check
        Date now = new Date();
        Period period = periodDao.selectByDateBetweenAndUser(now, user.getId());
        if(period == null){
            //now we need to create new period
            period = new Period();
            period.setUser(user);
            period.setPeriodType(PeriodType.FOURWEEK); // setting this by default, can be changed in settings

            setPeriodDateFourWeek(period, user.getId());

            period = periodDao.insert(period);

            //now we need calculate time-for-time for previous period

        } else {
            //every thing is ok
        }
    }

    public void setPeriodDateFourWeek(Period period, Long userId){
        //first we need to check for existing of last period
        Period lastPeriodByUser = periodDao.selectLastPeriodByUser(userId);
        Date startDate;
        if(period == null){
            //we just need to find first monday of current month
            startDate = DateTools.getDateOfFirstMonday();
        } else {
            //we need to find next date after end of last period
            startDate = DateTools.getDateNextDay(lastPeriodByUser.getEndDate());//this will be monday
        }
        period.setStartDate(startDate);
        period.setEndDate(DateTools.getDateAfterFourWeeks(startDate));
    }

    public void setPeriodDateMonth(Period period){
        Date startDate = DateTools.getDateOfFirstMonday();
        Date endDate = DateTools.getDateOfLastDay();
        if(!DateTools.isSunday(endDate)){
            //we need to find end of this week
            endDate = DateTools.getDateOfNextSunday(endDate);
        }
        period.setStartDate(startDate);
        period.setEndDate(endDate);
    }

}
