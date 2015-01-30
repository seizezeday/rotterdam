package com.rotterdam.controllers;

import com.rotterdam.dto.DeclarationsDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.DeclarationService;
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
 * @author Anatolii
 */
@Path("/declaration")
@PermitAll
@Named
public class DeclarationInfo {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private DeclarationService declarationService;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/get")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getDeclarations(@Context HttpServletRequest hsr, String data) throws JsonException, IOException, ParseException {

        Date monday = DateTools.getDateOfPrevMonday(jsonCommands.getDateFromJson(data));

        User user = jsonCommands.getUserFromRequest(hsr);

        DeclarationsDto declarationsDto = declarationService.selectByWeekIdAndUserId(monday, user.getId());

        return Response.ok(declarationsDto).build();
    }
    //{"currentDate":"29.01.2015"}

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/set")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response setDeclarations(@Context HttpServletRequest hsr, DeclarationsDto declarationsDto) throws JsonException, IOException, ParseException {

        User user = jsonCommands.getUserFromRequest(hsr);

        declarationService.save(declarationsDto, user.getId());

        return Response.ok().build();
    }
    //{"date":"29.01.2015", "declarations":[ {"costType":"SomeType","price":"5"}, {"costType":"SomeType2","price":"50"} ]}
}
