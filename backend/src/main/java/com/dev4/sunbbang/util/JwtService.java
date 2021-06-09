package com.dev4.sunbbang.util;

import org.springframework.stereotype.Service;

import com.dev4.sunbbang.model.AuthVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	private final JwtManager jwtManager;
	
	//토큰 발급
	public TokenDTO createToken(AuthVO authVO) {
		String accessToken = jwtManager.generateAccessToken(authVO);
		String refreshToken = jwtManager.generateRefreshToken(authVO);
		return new TokenDTO(accessToken, refreshToken, jwtManager.getValidationAccessTokenTime());
	}
	
	//refresh 토큰 갱신
	public TokenDTO createTokenByRefreshToken(String refreshToken) {
		AuthVO authVO = jwtManager.getAuthVO(refreshToken);
		String accessToken = jwtManager.generateAccessToken(authVO);
		String newRefreshToken = jwtManager.generateRefreshToken(authVO);
		return new TokenDTO(accessToken, newRefreshToken, jwtManager.getValidationAccessTokenTime());
	}
}
