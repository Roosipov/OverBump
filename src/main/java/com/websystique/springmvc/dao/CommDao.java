package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Comment;

public interface CommDao {

	Comment findById(int id);

	void saveComm(Comment user);

	List<Comment> findAllComments(int id);

	Comment findByCommId(int id);
}
