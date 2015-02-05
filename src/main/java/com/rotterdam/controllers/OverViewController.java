package com.rotterdam.controllers;

import com.rotterdam.dto.OverViewDetailDto;
import com.rotterdam.dto.OverViewDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.OverViewService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author vasax32
 */
@Path("/overView")
@PermitAll
@Named
public class OverViewController {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private OverViewService overViewService;


    //{"date":"29.01.2015", "usedWeeks":[true,true,true,true] }
    @RolesAllowed({ "Driver" })
    @POST
    @Path("/getPdf")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getOverView(@Context HttpServletRequest hsr, OverViewDto overViewDto) throws JsonException, IOException, ParseException {

        User user = jsonCommands.getUserFromRequest(hsr);

        OverViewDto overView = overViewService.getOverView(overViewDto, user.getId());

        return Response.ok(overView).build();
    }
    //{"weekOverViews":[
    // {"days":{}},
    // {"days":{}},
    // {"days":{}},
    // {"days":{
    //      "Monday":{"date":"26.01.2015","workHours":[{"startWorkingTime":"09:45","endWorkingTime":"22:00","restTime":120,"dayType":"1"}]},
//          "Tuesday":{"date":"27.01.2015","workHours":[{"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":0,"dayType":"1"}]},
//          "Wednesday":{"date":"28.01.2015","workHours":[{"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":0,"dayType":"1"}]},
//          "Thursday":{"date":"29.01.2015","workHours":[{"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":0,"dayType":"1"}]},
//          "Friday":{"date":"30.01.2015","workHours":[{"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":0,"dayType":"1"}]},
//          "Saturday":{"date":"31.01.2015","workHours":[{"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":0,"dayType":"1"}]},
//          "Sunday":{"date":"01.02.2015","workHours":[{"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":0,"dayType":"1"}]}}}
//      ],"usedWeeks":[true,true,true,true],"date":"29.01.2015"}

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/getDetail")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getOverViewDetail(@Context HttpServletRequest hsr, String data) throws JsonException, IOException, ParseException {

        Date monday = DateTools.getDateOfPrevMonday(jsonCommands.getDateFromJson(data));

        User user = jsonCommands.getUserFromRequest(hsr);

        OverViewDetailDto overViewDetail = overViewService.getOverViewDetail(monday, user.getId());

        return Response.ok(overViewDetail).build();
    }

}
