package com.ejbank.api;

import com.ejbank.entities.User;
import com.ejbank.user.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserResource {

    @EJB
    private UserService userService;

    @GET
    @Path("/{user_id}")
    public User getUserById(@PathParam("user_id") int userId) {
        return userService.getUserById(userId);
    }
}
