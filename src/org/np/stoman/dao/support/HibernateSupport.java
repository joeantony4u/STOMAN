package org.np.stoman.dao.support;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class HibernateSupport {

	private static HibernateSupport hs;
	private final ThreadLocal<Session> session;

	private HibernateSupport() {
		session = new ThreadLocal<Session>();
	}

	public synchronized static HibernateSupport getInstance() {
		if (hs == null)
			hs = new HibernateSupport();
		return hs;
	}

	public <T> void save(T t) {
		session.get().save(t);
	}

	public <T> T get(Class<T> t, int id) {
		return t.cast(session.get().load(t, id));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t) {
		return session.get().createCriteria(t).list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t, List<Criterion>... criterions) {
		Criteria criteria = session.get().createCriteria(t);
		addRestrictions(criteria, criterions);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t, List<Order> orders,
			List<Criterion>... criterions) {
		Criteria criteria = session.get().createCriteria(t);
		addRestrictions(criteria, criterions);
		addOrders(criteria, orders);
		return criteria.list();
	}

	private void addRestrictions(Criteria criteria, List<Criterion>[] criterions) {
		for (List<Criterion> lcriterion : criterions)
			for (Criterion criterion : lcriterion)
				criteria.add(criterion);
	}

	private void addOrders(Criteria criteria, List<Order> orders) {
		for (Order order : orders)
			criteria.addOrder(order);
	}

	public void setSession(Session session) {
		this.session.set(session);
	}
}
