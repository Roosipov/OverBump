package by.rosipov.springmvc.dao;

import java.util.List;

import by.rosipov.springmvc.model.Document;

public interface DocumentDao {

	List<Document> findAll();

	Document findById(int id);

	void save(Document document);

	List<Document> findAllByUserId(int userId);

	void deleteById(int id);
}