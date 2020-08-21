package com.project.brunch.domain.following;

import javax.persistence.Entity;
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
public class Following {

	@Id // 프라이머리키를 걸어주는 어노테이션이다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링전략
	private int id;
	private int userId;
	private String followingId;
}
