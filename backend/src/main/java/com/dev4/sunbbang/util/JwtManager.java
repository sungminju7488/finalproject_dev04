package com.dev4.sunbbang.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

//JWT를 관리할 매니저 클래스
public class JwtManager {
	// 토큰 유지시간(30분)
	private long ACCESS_TOKEN_VALIDATION_SECOND = 60 * 30;
	
	// 토큰 유지시간(1개월)
	private long REFRESH_TOKEN_VALIDATION_SECOND = 60 * 60 * 24 * 30;
	
	//암호화를 위한 시크릿 키
	private String secretKey;
	
	//시크릿 키만 받음
	public JwtManager(String secretKey) {
		this.secretKey = secretKey;
	}
	
	//시크릿 키 + 토큰 유효시간 조절
	public JwtManager(String secretKey, Long accessTokenValidationSecond, Long refreshTokenValidationSecond) {
		this.secretKey = secretKey;
		this.ACCESS_TOKEN_VALIDATION_SECOND = accessTokenValidationSecond;
		this.REFRESH_TOKEN_VALIDATION_SECOND = refreshTokenValidationSecond;
	}
	
	//인증 키값 얻기
	private Key getSigningKey(String secretkey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	//토큰 해석
	public Claims validTokenAndReturnBody(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(getSigningKey(this.secretKey))
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new InvalidParameterException("유효하지 않은 토큰입니다.");
		}
	}
	
	//유저 id 조회
	public String getMemberId(String token) {
		return validTokenAndReturnBody(token).get("memberId", String.class);
	}
	
	//유저 이름 조회
	public String getName(String token) {
		return validTokenAndReturnBody(token).get("name", String.class);
	}
	
	//토큰 만료
	private Boolean isTokenExpired(String token) {
		Date expiration = validTokenAndReturnBody(token).getExpiration();
		return expiration.before(new Date());
	}
}
