package com.ojt.springjdbc.bl.service.post;

import java.util.List;

import com.ojt.springjdbc.bl.dto.PostDTO;
import com.ojt.springjdbc.web.form.PostForm;

public interface PostService {
	List<PostDTO> getAllPosts();

	void savePost(PostForm postForm);

	PostDTO getPostById(Long id);

	void updatePost(PostForm postForm);

	void deletePostById(Long id);
	
	List<PostDTO> searchPosts(String search);
}
