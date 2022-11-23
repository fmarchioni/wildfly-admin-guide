 
package com.itbuzzpress.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

 
@SuppressWarnings("serial")
@WebServlet("/secure")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "Admin" }))
public class SecuredServlet extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Principal principal = null;
        String authType = null;
        String remoteUser = null;


        principal = req.getUserPrincipal();

        remoteUser = req.getRemoteUser();

        authType = req.getAuthType();

        writer.println("<html><head><title>servlet-security</title></head><body>");
        writer.println("<h1>" + "Successfully called Secured Servlet " + "</h1>");
        writer.println("<p>" + "Principal  : " + principal.getName() + "</p>");
        writer.println("<p>" + "Remote User : " + remoteUser + "</p>");
        writer.println("<p>" + "Authentication Type : " + authType + "</p>");
        writer.println("</body></html>");
        writer.close();
    }

}
