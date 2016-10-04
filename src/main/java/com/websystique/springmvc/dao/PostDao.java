package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.Message;
import com.websystique.springmvc.model.Post;

public interface PostDao {

	Post findById(int id);

	User findBySSO(String sso);

	void save(Post user);

	void save(Message message);

	void deleteBySSO(String sso);

	List<Post> findAllPosts();

	List<Post> postsByBumps();

	List<Post> allPosts(int postsAmount);

	List<Post> pinedPosts();

}
