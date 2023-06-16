package com.ojt.springjdbc.bl.service.post.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ojt.springjdbc.bl.dto.PostDTO;
import com.ojt.springjdbc.bl.service.post.PostService;
import com.ojt.springjdbc.persistence.entity.Post;
import com.ojt.springjdbc.web.form.PostForm;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PostDTO> getAllPosts() {
		String sql = "SELECT * FROM posts";
		List<Post> posts = jdbcTemplate.query(sql, (rs, rowNum) -> {
			Post post = new Post();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setDescription(rs.getString("description"));
			return post;
		});

		return posts.stream().map(item -> new PostDTO(item)).collect(Collectors.toList());
	}

	@Override
	public void savePost(PostForm postForm) {
		String sql = "INSERT INTO posts (title, description, created_at, updated_at) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, postForm.getTitle(), postForm.getDescription(), LocalDateTime.now(),
				LocalDateTime.now());
	}

	@SuppressWarnings("deprecation")
	@Override
	public PostDTO getPostById(Long id) {
		String sql = "SELECT * FROM posts WHERE id=?";
		Post postData = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
			Post post = new Post();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setDescription(rs.getString("description"));
			return post;
		});
		
		return new PostDTO(postData);
		
		
	}

	@Override
	public void updatePost(PostForm post) {
		String sql = "UPDATE posts SET title=?, description=?, updated_at=? WHERE id=?";
		jdbcTemplate.update(sql, post.getTitle(), post.getDescription(), LocalDateTime.now(), post.getId());
	}

	@Override
	public void deletePostById(Long postId) {
		String sql = "DELETE FROM posts WHERE id=?";
		jdbcTemplate.update(sql, postId);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<PostDTO> searchPosts(String search) {
		String sql = "SELECT * FROM posts WHERE title LIKE ? OR description LIKE ?";
        String searchPattern = "%" + search + "%";
        List<Post> posts =  jdbcTemplate.query(sql, new Object[]{searchPattern, searchPattern}, (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setTitle(rs.getString("title"));
            post.setDescription(rs.getString("description"));
            return post;
        });
        
        return posts.stream().map(item -> new PostDTO(item)).collect(Collectors.toList());
	}

}
