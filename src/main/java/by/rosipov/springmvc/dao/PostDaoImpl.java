package by.rosipov.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import by.rosipov.springmvc.dao.AbstractDao;
import by.rosipov.springmvc.dao.PostDao;

import by.rosipov.springmvc.model.Message;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.model.User;

@Repository("postDao")
public class PostDaoImpl extends AbstractDao<Integer, Post> implements PostDao {

	public Post findById(int id) {
		Post post = getByKey(id);
		return post;
	}

	@SuppressWarnings("unchecked")
	public List<Post> findAllPosts() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<Post> posts = (List<Post>) criteria.list();

		return posts;
	}

	@SuppressWarnings("unchecked")
	public List<Post> pinedPosts() {
		// Session session = new Session();
		// Criteria criteria =
		// createEntityCriteria().addOrder(Order.desc("bumps"));
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To
		// avoid duplicates.
		// List<Post> posts = criteria.add(Expression.)
		Criteria criteria = createEntityCriteria().add(Restrictions.gt("bumps", 10)).addOrder(Order.desc("bumps"));
		List<Post> pinPosts = criteria.list();
		// int listSize = posts.size();
		// for(int i = 0; i < listSize; ++i){
		// if (posts.get(i).getBumps() > 10)
		// pinPosts.add(posts.get(i));
		// }

		return pinPosts;
	}

	@SuppressWarnings("unchecked")
	public List<Post> allPosts(int postsAmount) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
		criteria.setFirstResult(0);
		criteria.setMaxResults(postsAmount);
		List<Post> posts = criteria.list();

		return posts;
	}

	@SuppressWarnings("unchecked")
	public List<Post> postsByBumps() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("bumps"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<Post> posts = (List<Post>) criteria.list();

		return posts;
	}

	public User findBySSO(String sso) {
		System.out.println("SSO : " + sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		return user;
	}

	public void save(Post post) {
		persist(post);
	}

	/*
	 * public void save(User user) { //persist(user); }
	 */

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		// delete(user);
	}

	@Override
	public void save(Message message) {
		getSession().persist(message);

	}

}