package com.project.brunch.domain.postimg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PostImg {

	@Id // 프라이머리키를 걸어주는 어노테이션이다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링전략
	private int id;
	private int userId;
	private int postId;
	private String imgURL; // 파싱해온 주소
}
