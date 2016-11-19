package by.rosipov.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import by.rosipov.springmvc.dao.AbstractDao;
import by.rosipov.springmvc.dao.MessDao;

import by.rosipov.springmvc.model.Message;
import by.rosipov.springmvc.model.Post;
import by.rosipov.springmvc.model.User;

public class MessDaoImpl extends AbstractDao<Integer, Message> implements MessDao {

	@Override
	public Message findById(int id) {
		Message message = getByKey(id);
		return message;
	}

	@Override
	public User findBySSO(String sso) {
		System.out.println("SSO : " + sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		return user;
	}

	@Override
	public void save(Message message) {
		persist(message);

	}

	@Override
	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User) crit.uniqueResult();
		// delete(user);

	}

	@Override
	public List<Post> findAllPosts() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<Post> posts = (List<Post>) criteria.list();

		return posts;
	}

}
