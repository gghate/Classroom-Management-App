package com.gghate.ExamAppl.Security;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import com.gghate.ExamAppl.Service.ApplicationUserRepositor;

public class TokenAuthenticationService {
	     private long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
	     private String secret = "ThisIsASecret";
	     private String headerString = "Authorization";
         @Autowired
	     ApplicationUserRepositor userRepo;
	     public void addAuthentication(HttpServletResponse response, String username) {
	         // We generate a token now.
	         String JWT = Jwts.builder()
	             .setSubject(username)
	             .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	             .signWith(SignatureAlgorithm.HS512, secret)
	             .compact();
	        
	         response.addHeader(headerString,JWT);
	         PrintWriter writer;
	         
			try {
				writer = response.getWriter();
				writer.append("{\"username\":"+"\""+username+"\",");
				writer.append("\"token\":"+"\""+JWT+"\"}");
				response.setContentType("application/json");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	        
	         
	     }
	     public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
	         String token = request.getHeader(headerString);
	         if (token != null) {
	             // parse the token.
	             String username = Jwts.parser()
	                 .setSigningKey(secret)
	                 .parseClaimsJws(token)
	                 .getBody()
	                 .getSubject();
	             if (username != null) // we managed to retrieve a user
	             {
	            	 return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
	             }
	         }
	         return null;
	     }
}
