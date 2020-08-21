package com.project.brunch.domain.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 데이터베이스 테이블의 모델이 된다.
public class User {
 
	@Id // 프라이머리키를 걸어주는 어노테이션이다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링전략
	private int id;
	private String nickName;
	private String snsId; // @앞 아이디 파싱한거
	private String password;
	private String email;  
	private String profileImg;
	private String bio; // 프로필 소개글
	private String snsType; // kakao, google, facebook
	// OAuth를 위해 구성한 추가 필드 2개
	private String provider;
	private String providerId;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
}
