package test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.np.stoman.dao.conf.HibernateUtil;
import org.np.stoman.dao.support.HibernateSupport;
import org.np.stoman.persistence.Vendors;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session s=HibernateUtil.openSession();
		Transaction tr=s.beginTransaction();
		Vendors v=new Vendors();
		v.setName("V2");
		HibernateSupport.getInstance().setSession(s);
		HibernateSupport.getInstance().save(v);
		tr.commit();
		s.close();
		System.out.println("After save");
		Session s1=HibernateUtil.openSession();
		HibernateSupport.getInstance().setSession(s1);
		v = HibernateSupport.getInstance().get(v.getClass()).get(0);
		System.out.println("Id"+v.getVendorId());
		s1.close();
	}

}
