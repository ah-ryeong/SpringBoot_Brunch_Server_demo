package com.project.brunch.domain.like;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostLike {

	@Id // 프라이머리키를 걸어주는 어노테이션이다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링전략
	private int id;
	
	private int userId; // 유저페이지에 내가 좋아요한 글 나오니까 필요
	private String likeType; // 좋아요 한 글
	private int likeCount; // 좋아요 수

}
