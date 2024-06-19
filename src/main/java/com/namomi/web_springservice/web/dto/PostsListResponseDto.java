package com.namomi.web_springservice.web.dto;

import java.time.LocalDateTime;

import com.namomi.web_springservice.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsListResponseDto {

	private Long id;
	private String title;
	private String author;
	private LocalDateTime modifiedDate;

	public PostsListResponseDto(Posts posts) {
		this.id = posts.getId();
		this.title = posts.getTitle();
		this.author = posts.getAuthor();
		this.modifiedDate = posts.getModifiedDate();
	}
}
