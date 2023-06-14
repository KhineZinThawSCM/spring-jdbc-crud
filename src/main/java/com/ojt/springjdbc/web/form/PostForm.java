package com.ojt.springjdbc.web.form;

import javax.validation.constraints.NotBlank;

import com.ojt.springjdbc.bl.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {
	private int id;

	@NotBlank(message = "Title field is required")
	private String title;

	@NotBlank(message = "Description field is required")
	private String description;

	public PostForm(PostDTO postDTO) {
		this.id = postDTO.getId();
		this.title = postDTO.getTitle();
		this.description = postDTO.getDescription();
	}
}
