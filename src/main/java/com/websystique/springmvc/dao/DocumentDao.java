package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Document;

public interface DocumentDao {

	List<Document> findAll();

	Document findById(int id);

	void save(Document document);

	List<Document> findAllByUserId(int userId);

	void deleteById(int id);
}