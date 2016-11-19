package by.rosipov.springmvc.dao;

import java.util.List;

import by.rosipov.springmvc.model.Comment;

public interface CommDao {

	Comment findById(int id);

	void saveComm(Comment user);

	List<Comment> findAllComments(int id);

	Comment findByCommId(int id);
}
