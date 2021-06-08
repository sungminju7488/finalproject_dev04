package com.dev4.sunbbang.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dev4.sunbbang.model.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
	public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;
	
	final static public String ACCESS_TOKEN_NAME = "accessToken";
	final static public String REFRESH_TOKEN_NAME = "refreshToken";
	
	@Value("${spring.jwt.secret}")
	private String SECRET_KEY;
	
	private Key getSigningKey(String secretKey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public Claims extractAllClaims(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey(SECRET_KEY))
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String GetMemberSeq(String token) {
		return extractAllClaims(token).get("memberSeq", String.class);
	}
	public String GetMemberId(String token) {
		return extractAllClaims(token).get("memberId", String.class);
	}
	public String GetName(String token) {
		return extractAllClaims(token).get("name", String.class);
	}
	public String GetNickName(String token) {
		return extractAllClaims(token).get("nickName", String.class);
	}
	public String GetGrade(String token) {
		return extractAllClaims(token).get("grade", String.class);
	}
	public String GetAlarmSet(String token) {
		return extractAllClaims(token).get("alarmSet", String.class);
	}
	public String GetFollowSet(String token) {
		return extractAllClaims(token).get("followSet", String.class);
	}
	
	//토큰 만료  여부 검사
	public Boolean isTokenExpired(String token) {
		final Date expiration = extractAllClaims(token).getExpiration();
		return expiration.before(new Date());
	}
	
	//토큰 생성(MemberVO 값을 보내 토큰생성)
	public String generateToken(MemberVO memberVO) {
		
	}
	
	//토큰 갱신(MemberVO 값을 보내 토큰갱신)
	public String generateRefreshToken(MemberVO memberVO) {
		
	}
	
	//토큰 값 세팅 및 생성
	public String doGenerateToken(int memberSeq, String memberId, String name, String nickName, String grade, String alarmSet, String followSet, long expireTime) {
		
		Claims claims = Jwts.claims();
		claims.put("memberSeq", memberSeq);
		claims.put("memberId", memberId);
		claims.put("name", name);
		claims.put("nickName", nickName);
		claims.put("grade", grade);
		claims.put("alarmSet", alarmSet);
		claims.put("followSet", followSet);
		
		String jwt = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
				.compact();
		
		return jwt;
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String name = GetName(token);
		
		return(name.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
