package com.project.brunch.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.brunch.config.auth.provider.KakaoUserInfo;
import com.project.brunch.config.auth.provider.OAuth2UserInfo;
import com.project.brunch.config.jwt.JwtProperties;
import com.project.brunch.domain.user.User;
import com.project.brunch.domain.user.UserRepository;
import com.project.brunch.domain.user.UserRole;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "/*")
@RequestMapping("brunch") // 컨트롤러 진입 주소
@RequiredArgsConstructor // final과 붙어있는 필드의 생성자를 다 만들어줌
public class JwtCreateController {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("oauth/jwt/kakao") 
	public String jwtCreate(@RequestBody Map<String, Object> data) {
		System.out.println("jwtCreate 실행됨");
		System.out.println(data.get("profilePbj"));
		
		OAuth2UserInfo kakaoUser = new KakaoUserInfo((Map<String, Object>)data.get("profileObj"));
		
		User userEntity = userRepository.findById(kakaoUser.getProvider()+"_"+kakaoUser.getProviderId());
		
		if (userEntity == null) {
			User userRequest = User.builder()
					.snsId(kakaoUser.getProvider()+"_"+kakaoUser.getProviderId())
					.email(kakaoUser.getEmail())
					.nickName(kakaoUser.getName())
					.provider(kakaoUser.getProvider())
					.providerId(kakaoUser.getProviderId())
					.userRole(UserRole.USER) 
					.build();
			
			userEntity = userRepository.save(userRequest);
		}
		
		String jwtToken = JWT.create()
				.withSubject(userEntity.getSnsId())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.withClaim("id", userEntity.getSnsId())
				.withClaim("nickName", userEntity.getNickName())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		return jwtToken;
	}
}
