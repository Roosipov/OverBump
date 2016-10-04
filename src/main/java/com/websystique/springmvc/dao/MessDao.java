package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Post;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.Message;

public interface MessDao {

	Message findById(int id);

	User findBySSO(String sso);

	void save(Message message);

	void deleteBySSO(String sso);

	List<Post> findAllPosts();
}
