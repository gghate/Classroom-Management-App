package com.gghate.ExamAppl.Security;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


public class JWTAuthenticationFilter  extends  BasicAuthenticationFilter {
    public TokenAuthenticationService tokenAuthenticationService;
    private String HEADER_STRING = "Authorization";
    private String TOKEN_PREFIX = "Bearer";
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        
        tokenAuthenticationService = new TokenAuthenticationService();
   }
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest req,
	                                    HttpServletResponse res,
	                                    FilterChain chain) throws IOException, ServletException {
	        String header = req.getHeader(HEADER_STRING);

	        if (header == null || header.startsWith(TOKEN_PREFIX)) {
	        	System.out.println("I am here");
	            chain.doFilter(req, res);
	            return;
	        }

	        UsernamePasswordAuthenticationToken authentication = tokenAuthenticationService.getAuthentication(req);

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        chain.doFilter(req, res);
	    }

}
