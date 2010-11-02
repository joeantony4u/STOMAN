package org.np.stoman.dao.support;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class HibernateSupport {
	
	private static HibernateSupport hs;
	private final ThreadLocal<Session> session;
	
	private HibernateSupport() {
		session = new ThreadLocal<Session>();
	}
	
	public synchronized static HibernateSupport getInstance() {
		if(hs == null)
			hs = new HibernateSupport();
		return hs;
	}
	
	public <T> void save(T t) {
		session.get().save(t);
	}
	
	public <T> T get(Class<T> t, int id){
		return t.cast(session.get().load(t, id));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> t) {
		return session.get().createCriteria(t).list();
	}
	
	public List<Criterion> restrict(Restrict restrict, Object[]... values) {
		List<Criterion> criterions = new LinkedList<Criterion>();
		for(Object[] value : values) {
			switch (restrict) {
			case IN:
				criterions.add(Restrictions.in((String)value[0], (Collection)value[1]));
				break;

			case NOTIN:
				Restrictions.not(Restrictions.in((String)value[0], (Collection)value[1]));
				break;
			}
			
		}
		return criterions;
	}
	
	public void setSession(Session session) {
		this.session.set(session);
	}
}
