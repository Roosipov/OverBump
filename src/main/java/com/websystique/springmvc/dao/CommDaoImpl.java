package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Comment;

@Repository("commDao")
public class CommDaoImpl extends AbstractDao<Integer, Comment> implements CommDao {

	@Override
	public Comment findById(int id) {
		Comment comment = findById(id);
		return comment;
	}

	@Override
	public void saveComm(Comment comment) {
		persist(comment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findAllComments(int id) {
		Criteria criteria = createEntityCriteria().add(Restrictions.like("commID", id)); // addOrder(Order.asc("commID"));
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To
		// avoid duplicates.
		List<Comment> comments = (List<Comment>) criteria.list();

		return comments;
	}

	@Override
	public Comment findByCommId(int id) {
		Comment comment = findById(id);
		return comment;
	}

}
