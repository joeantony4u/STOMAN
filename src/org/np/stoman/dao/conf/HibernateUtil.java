package org.np.stoman.dao.conf;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sf;
	
	static {
		//TODO: Exception Handling
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	public synchronized static Session openSession() {
		return sf.openSession();
	}
}
