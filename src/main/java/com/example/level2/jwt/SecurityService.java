package com.example.level2.jwt;

//@Service("jwtService")
public interface SecurityService {
	
	String createJWT(String subject, long ttlMillis);
	String verifyJWT(String token);
}
