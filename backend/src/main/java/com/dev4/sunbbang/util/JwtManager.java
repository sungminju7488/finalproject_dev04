package com.dev4.sunbbang.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dev4.sunbbang.model.AuthVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

//JWT를 관리할 매니저 클래스
@Configuration
public class JwtManager {
	
	// 토큰 유지시간(30분)
	private long ACCESS_TOKEN_VALIDATION_SECOND = 60 * 30;
	
	// 토큰 유지시간(1개월)
	private long REFRESH_TOKEN_VALIDATION_SECOND = 60 * 60 * 24 * 30;
	
	//암호화를 위한 시크릿 키(secret key가 짧으면 에러가 남)
	private final String secretKey;
	
	@Bean
	public JwtManager BeanjwtManager() {
		String secretKey = "secretKey-finalproject-dev04-jwt-manager-token";
		Long accessTokenExpireSecond = 60L * 60L * 24L; //1일
		Long refreshTokenExpireSecond = 60L * 60L * 24L * 30L;//1개월
		return new JwtManager(secretKey, accessTokenExpireSecond, refreshTokenExpireSecond);
	}
	
	public JwtManager() {
		this.secretKey = "secretKey-finalproject-dev04-jwt-manager-token";
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
	
	//유저 seq 조회
	public int getMemberSeq(String token) {
		return validTokenAndReturnBody(token).get("memberSeq", Integer.class);
	}
	
	//유저 id 조회
	public String getMemberId(String token) {
		return validTokenAndReturnBody(token).get("memberId", String.class);
	}
	
	//유저 이름 조회
	public String getName(String token) {
		return validTokenAndReturnBody(token).get("name", String.class);
	}
	
	//유저 닉네임 조회
	public String getNickName(String token) {
		return validTokenAndReturnBody(token).get("nickName", String.class);
	}
	
	//유저 등급 조회
	public String getGrade(String token) {
		return validTokenAndReturnBody(token).get("grade", String.class);
	}
	
	//유저 알람목록 조회
	public String getAlarmSet(String token) {
		return validTokenAndReturnBody(token).get("alarmSet", String.class);
	}
	
	//유저 팔로우목록 조회
	public String getFollowSet(String token) {
		return validTokenAndReturnBody(token).get("followSet", String.class);
	}
	
	//사업자 번호 조회
	public String getCopRegNum(String token) {
		return validTokenAndReturnBody(token).get("copRegNum", String.class);
	}
	
	//Auth 반환
	public AuthVO getAuthVO(String token) {
		AuthVO authVO = new AuthVO();
		authVO.setMemberSeq(getMemberSeq(token));
		authVO.setMemberId(getMemberId(token));
		authVO.setName(getName(token));
		authVO.setNickName(getNickName(token));
		authVO.setGrade(getGrade(token));
		authVO.setAlarmSet(getAlarmSet(token));
		authVO.setFollowSet(getFollowSet(token));
		authVO.setCopRegNum(getCopRegNum(token));
		return authVO;
	}
	
	
	//토큰 만료
	private Boolean isTokenExpired(String token) {
		Date expiration = validTokenAndReturnBody(token).getExpiration();
		return expiration.before(new Date());
	}
	
	//access token 생성
	public String generateAccessToken(AuthVO authVO) {
		return doGenerateToken(authVO, ACCESS_TOKEN_VALIDATION_SECOND * 1000L);
	}
	
	//refresh token 생성
	public String generateRefreshToken(AuthVO authVO) {
		return doGenerateToken(authVO, REFRESH_TOKEN_VALIDATION_SECOND * 1000L);
	}
	
	//accessToken 유효시간 알림(second)
	public Long getValidationAccessTokenTime() {
		return ACCESS_TOKEN_VALIDATION_SECOND;
	}
	
	//토큰 생성
	private String doGenerateToken(AuthVO authVO, Long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("memberSeq", authVO.getMemberSeq());
		claims.put("memberId", authVO.getMemberId());
		claims.put("name", authVO.getName());
		claims.put("nickName", authVO.getNickName());
		claims.put("grade", authVO.getGrade());
		claims.put("alarmSet", authVO.getAlarmSet());
		claims.put("followSet", authVO.getFollowSet());
		claims.put("copRegNum", authVO.getCopRegNum());
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey(this.secretKey), SignatureAlgorithm.HS256)
				.compact();
	}
}
