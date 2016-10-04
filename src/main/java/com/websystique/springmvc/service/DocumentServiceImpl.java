package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.DocumentDao;
import com.websystique.springmvc.model.Document;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	DocumentDao dao;

	public Document findById(int id) {
		return dao.findById(id);
	}

	public List<Document> findAll() {
		return dao.findAll();
	}

	public List<Document> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}

	public void saveDocument(Document document) {
		dao.save(document);
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

}