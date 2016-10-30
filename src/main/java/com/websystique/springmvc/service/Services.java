package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Comment;
import com.websystique.springmvc.model.Message;
import com.websystique.springmvc.model.Post;
import com.websystique.springmvc.model.User;

public interface Services {

	Post findById(int id);

	User findBySSO(String sso);

	void savePost(Post post);

	void bump(Post post);

	void deleteUserBySSO(String sso);

	List<Post> findAllPosts();

	List<Post> postByBumps();

	boolean isUserSSOUnique(Integer id, String sso);

	Comment findCommById(int id);

	List<Comment> findAllComments(int id);

	void saveComment(Comment comment);

	void saveMessage(Message message);

	Message findMessById(int id);

	void sage(Post post);
	
	void setBitChain (Post post) ;

	void viewup(Post post);

	void commentup(Post post);

	List<Post> allPosts(int postsAmount);

	List<Post> pinedPosts();

}