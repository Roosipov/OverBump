package by.rosipov.springmvc.service;

import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.rosipov.springmvc.service.Services;

import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import by.rosipov.springmvc.controller.ImageProcessing;
import by.rosipov.springmvc.dao.CommDao;
import by.rosipov.springmvc.dao.PostDao;
import by.rosipov.springmvc.model.Comment;
import by.rosipov.springmvc.model.Message;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.model.User;

@Service("service")
@Transactional
public class ServiceImpl implements Services {

	@Autowired
	private PostDao dao;

	@Autowired
	private CommDao daoComm;

	// @Autowired
	// private MessDao daoMess;

	@Override
	public Comment findCommById(int id) {

		return daoComm.findByCommId(id);
	}

	@Override
	public List<Comment> findAllComments(int id) {

		return daoComm.findAllComments(id);
	}

	@Override
	public void saveComment(Comment comment) {
		daoComm.saveComm(comment);

	}

	public Post findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String sso) {
		User user = dao.findBySSO(sso);
		return user;
	}

	public void savePost(Post post) {
		dao.save(post);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	public void bump(Post post) {
		int b = post.getBumps();
		post.equals(post);
		Post entity = dao.findById(post.getId());
		if (entity != null) {
			entity.setBumps(b + 1);
		}
	}

	public void setBitChain(Post post) {
		
		InputStream in = new ByteArrayInputStream(post.getContent());
		try {
			BufferedImage bImage = ImageIO.read(in);
			post.equals(post);
			Post entity = dao.findById(post.getId());
			bImage = ImageProcessing.scale(bImage, 8, 8);
			int avrColor = ImageProcessing.averageColor(bImage);
			if (entity != null) {
				entity.setBitChain(ImageProcessing.bitChain(bImage, avrColor));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void sage(Post post) {
		int b = post.getSage();
		post.equals(post);
		Post entity = dao.findById(post.getId());
		if (entity != null) {
			entity.setSage(b + 1);
		}
	}

	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<Post> findAllPosts() {
		return dao.findAllPosts();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public void saveMessage(Message message) {
		dao.save(message);
	}

	@Override
	public Message findMessById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void viewup(Post post) {
		int b = post.getViews();
		post.equals(post);
		Post entity = dao.findById(post.getId());
		if (entity != null) {
			entity.setViews(b + 1);
		}

	}

	@Override
	public void commentup(Post post) {
		int b = post.getComments();
		post.equals(post);
		Post entity = dao.findById(post.getId());
		if (entity != null) {
			entity.setComments(b + 1);
		}

	}

	@Override
	public List<Post> postByBumps() {
		return dao.postsByBumps();
	}

	@Override
	public List<Post> allPosts(int postsAmount) {
		return dao.allPosts(postsAmount);
	}

	@Override
	public List<Post> pinedPosts() {
		return dao.pinedPosts();
	}

}