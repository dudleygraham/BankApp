//package com.learning.security.jwt;
//
//import org.slf4j.LoggerFactory;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import com.learning.security.service.UserDetailsImpl;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//@Component
//public class JwtUtils {
//	private static final Logger Logger = LoggerFactory.getLogger(JwtUtils.class);
//
//	@Value("${com.cogent.fooddeliveryapp.jwtSecret}")
//	private String jwtSecret;
//
//	@Value("${com.cogent.fooddeliveryapp.jwtExpirationMs}")
//	private Long jwtExpirationMs;
//
//	public String generateToken(Authentication authentication) {
//		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//		// It is using builder DP
//		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
//				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//	}
//
//	// validation of token
//
//	public boolean validateJwtToken(String authToken) {
//		try {
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//			return true;
//		} catch (ExpiredJwtException e) {
//			Logger.error("JWT token is expired: {}", e.getMessage());
//
//		} catch (UnsupportedJwtException e) {
//			//Logger.error("JWT token is unsupported : {}",e.getMessage());
//			e.printStackTrace();
//		} catch (MalformedJwtException e) {
//			Logger.error("Invalid JWT token",e.getMessage());
//			e.printStackTrace();
//		} catch (SignatureException e) {
//			Logger.error("Invalid Signature", e.getMessage());
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			Logger.error("Invalid Argument passed",e.getMessage());
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	public String getUsernameFromJwtToken(String authToken) {
//		return Jwts.parser()
//				.setSigningKey(jwtSecret)
//				.parseClaimsJws(authToken)
//				.getBody()
//				.getSubject();
//		
//	}
//
//}
