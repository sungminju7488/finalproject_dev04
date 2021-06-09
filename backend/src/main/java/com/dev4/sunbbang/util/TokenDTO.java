package com.dev4.sunbbang.util;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class TokenDTO {
	@NonNull
	private String accessToken;//접근토큰
	@NonNull
	private String refreshToken;//갱신토큰
	@NonNull
	private Long expireSec;//만료시간
	
	public TokenDTO(@NonNull String accessToken, @NonNull String refreshToken, @NonNull Long expireSec) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expireSec = expireSec;
	}

}
