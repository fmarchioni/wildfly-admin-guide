 
package com.itbuzzpress.security;

import java.security.Principal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

 
@Stateless
@Remote(SecuredEJBRemote.class)
@RolesAllowed({ "guest" })
@SecurityDomain("other")
public class SecuredEJB implements SecuredEJBRemote {

    // Inject the Session Context
    @Resource
    private SessionContext ctx;

 
    public String getSecurityInfo() {
 
        Principal principal = ctx.getCallerPrincipal();
        return principal.toString();
    }

    @RolesAllowed("manager")
    public boolean secured() {
        return true;
    }
}
