package com.obc.obc.Service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler 
{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,Authentication authentication) throws IOException,ServletException
    {
        var authourities = authentication.getAuthorities();
        var Application = authourities.stream().map(r ->r.getAuthority()).findFirst();
        System.out.println(Application);
        if (Application.orElse("").equals("ADMIN")) 
        {
			response.sendRedirect("/Admindashboard");
		} 
        else if (Application.orElse("").equals("USER")) 
        {
			response.sendRedirect("/userdashboard");
		} 
        else 
        {
			response.sendRedirect("/error");
		}
    }
}
