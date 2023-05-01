package com.athi.eba.service;
 
/*import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;*/
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.athi.eba.model.User;
import jakarta.ws.rs.*;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user-management")
@Path("/user-management")
public class UserService
{
    @GET
    @Path("/users/{id}")
    public User getUserByIdUsingPathParam(@PathParam("id") Integer id)
    {
        User user = new User();
        user.setId(id);
        user.setFirstName("demo");
        user.setLastName("user");
        return user;
    }
    
    @GET
    @Path("/user")
    public User getUserByIdUsingQueryParam(@QueryParam("id") Integer id)
    {
        User user = new User();
        user.setId(id);
        user.setFirstName("demo");
        user.setLastName("user");
        return user;
    }
    
    @GET
    @Path("/user")
    public User getUserByIdUsingMatrixParam(@MatrixParam("id") Integer id)
    {
        User user = new User();
        user.setId(id);
        user.setFirstName("demo");
        user.setLastName("user");
        return user;
    }
    
    @GET
    @Path("/user")
    public User getUserByIdUsingFormParam(@FormParam("id") Integer id) 
    {
        User user = new User();
        user.setId(id);
        user.setFirstName("demo");
        user.setLastName("user");
        return user;
    }
    
    /*@GET
    @Path("/user")
    public User getLoggedInUserUsingCookieParam(@CookieParam("id") javax.ws.rs.core.Cookie id) 
    {
        User user = new User();
        user.setId(Integer.parseInt(id.getValue()));
        user.setFirstName("demo");
        user.setLastName("user");
        return user;
    }*/
}