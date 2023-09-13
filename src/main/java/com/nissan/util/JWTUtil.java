package com.nissan.util;

import java.nio.file.AccessDeniedException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.nissan.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
  //secret key
	private static String secret="this_is_java";
	
	//expiration time
	private static long expiryDuration=60*60;
	
	//generate token:header.payload.signature
	public String generateJWT(User user) {
		long milliTime=System.currentTimeMillis();
		long expiryTime=milliTime+expiryDuration*1000;
		
		//set issuedTime and ExpiryTimein token
		Date issuedAt=new Date(milliTime);
		Date expiryAt=new Date(expiryTime);
		
		//claims
		Claims claim=Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt).setExpiration(expiryAt);;
		
		//generate jwt token using claims
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

//checking token is valid or not
public Claims verify(String authorization) throws AccessDeniedException {
	try {
		Claims claim=Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
		return claim;
	}catch(Exception e) {
		throw new AccessDeniedException("Sorry! ACCESS DENIED!!!");
	}
}
}