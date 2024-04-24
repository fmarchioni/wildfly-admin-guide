 
package com.itbuzzpress.security;

import java.security.Principal;

import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Remote;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

 
@Stateless
@Remote(SecuredEJBRemote.class)
@RolesAllowed({ "employee" })
@SecurityDomain("EJBDomain")
public class SecuredEJB implements SecuredEJBRemote {

    // Inject the Session Context
    @Resource
    private SessionContext ctx;

 
    public String getSecurityInfo() {
 
        Principal principal = ctx.getCallerPrincipal();
        return principal.toString();
    }

    @RolesAllowed("admin")
    public boolean secured() {
        return true;
    }
}
