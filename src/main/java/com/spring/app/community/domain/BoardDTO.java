package com.spring.app.community.domain;

import lombok.Data;

@Data
public class BoardDTO {

	private Long boardId;
	private String title;
	private int postCount;
}