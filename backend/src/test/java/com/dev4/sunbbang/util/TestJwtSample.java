package com.dev4.sunbbang.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dev4.sunbbang.model.AuthVO;

import io.jsonwebtoken.Claims;
@ExtendWith(SpringExtension.class)
class TestJwtSample {
//	//secret key가 짧으면 에러가 남
//	private final String secretKey = "secretKey-finalproject-dev04-jwt-manager-token";
//	
//	
//	@Test
//	@DisplayName("토큰 정상 발급")
//	void successTest() {
//		//given
//		JwtManager jwtManager = new JwtManager(secretKey, 5L, 60L);
//		
//		//when
//		AuthVO authVO = new AuthVO();
//		authVO.setMemberSeq(1);
//		authVO.setMemberId("hou");
//		authVO.setName("호우");
//		authVO.setNickName("houhou");
//		authVO.setGrade("0");
//		authVO.setAlarmSet("알람목록");
//		authVO.setFollowSet("팔로우목록");
//		String accessToken = jwtManager.generateAccessToken(authVO);
//		
//		Claims claims = jwtManager.validTokenAndReturnBody(accessToken);
//		System.out.println("claims: " + claims);
//		String findName = claims.get("name", String.class);
//		
//		//then
//		assertThat(authVO.getName()).isEqualTo(jwtManager.getName(accessToken));
//		assertThat(authVO.getName()).isEqualTo(findName);
//	}
//	
//	@Test
//	@DisplayName("토큰유효시간 over")
//	void expireTokenTest() throws InterruptedException {
//		//given
//		JwtManager jwtManager = new JwtManager(secretKey, 1L, 60L);
//		AuthVO authVO = new AuthVO();
//		authVO.setMemberSeq(1);
//		authVO.setMemberId("hou");
//		authVO.setName("호우");
//		authVO.setNickName("houhou");
//		authVO.setGrade("0");
//		authVO.setAlarmSet("알람목록");
//		authVO.setFollowSet("팔로우목록");
//		String accessToken = jwtManager.generateAccessToken(authVO);
//		
//		//2초 딜레이
//		Thread.sleep(2000L);
//		
//		//when
//		InvalidParameterException ex = assertThrows(InvalidParameterException.class, () -> jwtManager.validTokenAndReturnBody(accessToken));
//		
//		//then
//		assertThat(ex.getMessage()).isEqualTo("유효하지 않은 토큰입니다.");
//	}

}
