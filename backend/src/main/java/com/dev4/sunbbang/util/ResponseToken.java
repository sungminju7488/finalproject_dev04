package com.dev4.sunbbang.util;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ResponseToken {
	@NonNull
	private String accessToken;
	@NonNull
	private String refreshToken;
	@NonNull
	private Long expireSec;
	
	public ResponseToken(TokenDTO tokenDTO) {
		this.accessToken = tokenDTO.getAccessToken();
		this.refreshToken = tokenDTO.getRefreshToken();
		this.expireSec = tokenDTO.getExpireSec();
	}
}
