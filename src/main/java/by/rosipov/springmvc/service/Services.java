package by.rosipov.springmvc.service;

import java.util.List;

import by.rosipov.springmvc.model.Comment;
import by.rosipov.springmvc.model.Message;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.model.User;

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