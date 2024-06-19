package com.namomi.web_springservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namomi.web_springservice.domain.posts.Posts;
import com.namomi.web_springservice.domain.posts.PostsRepository;
import com.namomi.web_springservice.web.dto.PostsListResponseDto;
import com.namomi.web_springservice.web.dto.PostsResponseDto;
import com.namomi.web_springservice.web.dto.PostsSaveRequestDto;
import com.namomi.web_springservice.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = getPosts(id);

		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}

	@Transactional(readOnly = true)
	public PostsResponseDto findById(Long id) {
		Posts posts = getPosts(id);

		return new PostsResponseDto(posts);
	}

	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
			.map(PostsListResponseDto::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = getPosts(id);
		postsRepository.delete(posts);
	}

	private Posts getPosts(Long id) {
		return postsRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 게시글이없습니다. id=" + id));
	}
}
