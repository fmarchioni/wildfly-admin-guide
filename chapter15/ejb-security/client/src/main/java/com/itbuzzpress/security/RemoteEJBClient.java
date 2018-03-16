 
package com.itbuzzpress.security;

import java.util.Hashtable;

import javax.ejb.EJBAccessException;
import javax.naming.Context;
import javax.naming.InitialContext;

 
public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {

        final Hashtable jndiProperties = new Hashtable();
 
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
        final Context context = new InitialContext(jndiProperties);

        SecuredEJBRemote reference = (SecuredEJBRemote) context.lookup("ejb:/ejb-remote-server/SecuredEJB!"
                + SecuredEJBRemote.class.getName());

        System.out.println("Successfully called secured bean, caller principal " + reference.getSecurityInfo());
        boolean hasFullPermission = false;
        try {
            hasFullPermission = reference.secured();
        } catch (EJBAccessException e) {
        }
        System.out.println("\nPrincipal has admin permission: " + hasFullPermission);
        
    }

}
