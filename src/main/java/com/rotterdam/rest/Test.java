package com.rotterdam.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by home on 15.11.14.
 */
@Path("/com/rotterdam/rest")
public class Test {

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "all is ok";
    }
}
