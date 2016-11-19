package by.rosipov.springmvc.dao;

import java.util.List;

import by.rosipov.springmvc.model.Message;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.model.User;

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
