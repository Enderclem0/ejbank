package com.ejbank.api;

import com.ejbank.api.payload.UserPayload;
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
    public UserPayload getUserById(@PathParam("user_id") int userId) {
        var user = userService.getUserById(userId);
        if (user == null) {
            return null;
        }
        return new UserPayload(user.getFirstname(), user.getLastname());
    }
}
