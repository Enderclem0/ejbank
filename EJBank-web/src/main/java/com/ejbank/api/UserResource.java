package com.ejbank.api;

import com.ejbank.payloads.UserDTO;
import com.ejbank.ejbs.user.UserService;

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
    public UserDTO getUserById(@PathParam("user_id") int userId) {
        var userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) return null;
        var user = userOptional.get();
        return new UserDTO(user.getFirstname(), user.getLastname());
    }
}
