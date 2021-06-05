package com.dev4.sunbbang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.dev4.sunbbang.member.MemberRepository;
import com.dev4.sunbbang.member.MemberService;
import com.dev4.sunbbang.model.MemberVO;

@SpringBootTest
class BackendApplicationTests {
	@Autowired
	MemberService ms;
	@Autowired
	MemberRepository mr;
	public void findpassword() {
		ms.findPassword();
	}



	
	
	
	


}
