package com.ejbank.api;

import com.ejbank.user.UserService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @EJB
    private UserService userService;

    @GET
    @Path("/{user_id}")
    public String getUserById(@PathParam("user_id") String userId) {
        return userService.getUserById(userId);
    }
}
