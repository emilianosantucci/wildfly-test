package com.example.demo;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJBContext;
import jakarta.ejb.LocalBean;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebFilter;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Stateless
@LocalBean
@Path("/hello-world")
public class HelloResource {
    @Resource
    private SessionContext sessionContext;

    @Resource
    private EJBContext ejbContext;

    @GET
    @PermitAll()
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @PermitAll()
    @Path("/unsecured")
    @Produces("text/plain")
    public String unsecured(){
//        return "Secured";
        return sessionContext == null ?
                "No session context! " :
                "Hello " + sessionContext.getCallerPrincipal().getName() + "!";
    }

    @GET
    @Path("/admin")
    @RolesAllowed({"Admin", "Guest"})
    @Produces("text/plain")
    public String secured(){
//        return "Secured";
        return sessionContext == null ?
                "No session context! " :
                "Hello " + sessionContext.getCallerPrincipal().getName() + "!";
    }

    @GET
    @Path("/youShouldNotPass")
    @RolesAllowed({"Pippo"})
    @Produces("text/plain")
    public String youShouldNotPass(){
//        return "Secured";
        return sessionContext == null ?
                "No session context! " :
                "Hello " + sessionContext.getCallerPrincipal().getName() + "!";
    }
}