package com.ojt.springjdbc.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ojt.springjdbc.bl.dto.PostDTO;
import com.ojt.springjdbc.bl.service.post.PostService;
import com.ojt.springjdbc.web.form.PostForm;

@Controller
public class PostController {
	@Autowired
	private PostService postService;

	@RequestMapping("/posts/list")
	public ModelAndView getAllPosts() {
		List<PostDTO> postDtoList = this.postService.getAllPosts();
		ModelAndView mv = new ModelAndView("postListView");
		mv.addObject("posts", postDtoList);
		return mv;
	}

	@RequestMapping("/posts/create")
	public ModelAndView createPost() {
		ModelAndView mv = new ModelAndView("addPostView");
		mv.addObject("postForm", new PostForm());
		return mv;
	}

	@RequestMapping(value = "/posts/store", method = RequestMethod.POST)
	public ModelAndView storePost(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("addPostView");
			return mv;
		}

		this.postService.savePost(postForm);
		mv.setViewName("redirect:/posts/list");
		return mv;
	}

	@RequestMapping("/posts/edit")
	public ModelAndView editPost(@RequestParam("id") Long id) {
		PostDTO postDTO = this.postService.getPostById(id);
		ModelAndView mv = new ModelAndView("editPostView");
		mv.addObject("postForm", new PostForm(postDTO));
		return mv;
	}

	@RequestMapping(value = "/posts/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("editPostView");
			return mv;
		}

		this.postService.updatePost(postForm);
		mv.setViewName("redirect:/posts/list");
		return mv;
	}

	@RequestMapping("/posts/destroy")
	public String destroyPost(@RequestParam("id") Long id) {
		this.postService.deletePostById(id);

		return "redirect:/posts/list";
	}
	
	@RequestMapping("/posts/search")
    public ModelAndView searchPosts(@RequestParam("keyword") String search) {
		List<PostDTO> postDtoList = this.postService.searchPosts(search);
		ModelAndView mv = new ModelAndView("postListView");
		mv.addObject("posts", postDtoList);
		return mv;
    }
}
