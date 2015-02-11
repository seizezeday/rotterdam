package com.rotterdam.controllers;

import com.rotterdam.dto.DeclarationsDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.DeclarationService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.DateParameter;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author vasax32
 */
@Path("/declaration")
@PermitAll
@Named
public class DeclarationController {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private DeclarationService declarationService;

    //{"currentDate":"29.01.2015"}
    @RolesAllowed({ "Driver" })
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getDeclarations(@Context HttpServletRequest hsr,@QueryParam("date") DateParameter date) throws JsonException, IOException, ParseException {

        Date monday = DateTools.getDateOfPrevMonday(date.getDate());

        User user = jsonCommands.getUserFromRequest(hsr);

        DeclarationsDto declarationsDto = declarationService.selectByWeekIdAndUserId(monday, user.getId());

        if(declarationsDto == null) {
            declarationsDto = new DeclarationsDto();
            if (declarationsDto.daysDeclaration == null || declarationsDto.daysDeclaration.size() == 0) {
                declarationsDto.daysDeclaration = declarationService.getFakeDeclarations(monday);
            }
        }
        declarationsDto.active = declarationService.isActive(monday, user.getId());
        return Response.ok(declarationsDto).build();
    }

    //{"date":"29.01.2015", "declarations":[ {"costType":"SomeType","price":"5"}, {"costType":"SomeType2","price":"50"} ]}

    @RolesAllowed({ "Driver" })
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response setDeclarations(@Context HttpServletRequest hsr, DeclarationsDto declarationsDto) throws JsonException, IOException, ParseException {

        User user = jsonCommands.getUserFromRequest(hsr);

        declarationService.save(declarationsDto, user.getId());

        return Response.ok().build();
    }

}
