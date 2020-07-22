package com.tzy.filter;

import com.tzy.model.Customer;
import com.tzy.service.CustomerService;
import com.tzy.service.JWTService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 1.extract authorization header
 2.remove bearer to get token
 3.decrypt token to get claim
 4.verify username info in our DB from claim
 5.doFilter dispatch **/
@WebFilter(urlPatterns = {"/*"}, filterName = "securityFilter", dispatcherTypes = DispatcherType.REQUEST)
public class SecurityFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(getClass());
    private String AUTH_URI = "/login";
    private String SIGN_UP_URI = "/sign_up";

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomerService customerService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        int status = authorization((HttpServletRequest) servletRequest);
        if(status == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(servletRequest, servletResponse);
        else
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);


    }


    private int authorization(HttpServletRequest req){

        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        String verb = req.getMethod();
        if(uri.equalsIgnoreCase(AUTH_URI) || uri.equalsIgnoreCase(SIGN_UP_URI)) return HttpServletResponse.SC_ACCEPTED;

        try{
            String token = req.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty()) return statusCode;

            Claims claims = jwtService.decryptJwtToken(token);
            if(claims.getId() != null){
                Customer c = customerService.getById(Long.valueOf(claims.getId())); // check if user exist
                if(c == null) return statusCode;
                //statusCode = HttpServletResponse.SC_ACCEPTED;
            }

            String allowedResources = "/"; //role-based authorization
            switch (verb){
                case "GET"    : allowedResources = (String)claims.get("allowedReadResources");   break;
                case "POST"   : allowedResources = (String)claims.get("allowedCreateResources"); break;
                case "PUT"    : allowedResources = (String)claims.get("allowedUpdateResources"); break;
                case "DELETE" : allowedResources = (String)claims.get("allowedDeleteResources"); break;
            }

            for (String s : allowedResources.split(",")) { //
                if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                    break;
                }
            }


        } catch(Exception e){
            logger.error("can't verify the token", e);

        }
        return statusCode;
    }

    @Override
    public void destroy() {

    }
}
