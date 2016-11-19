package by.rosipov.springmvc.service;

import java.util.List;

import by.rosipov.springmvc.model.Document;

public interface DocumentService {

	Document findById(int id);

	List<Document> findAll();

	List<Document> findAllByUserId(int id);

	void saveDocument(Document document);

	void deleteById(int id);
}