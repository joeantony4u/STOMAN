package test;

import static org.np.stoman.dao.support.FieldConstants.ADDRESS;
import static org.np.stoman.dao.support.FieldConstants.NAME;
import static org.np.stoman.dao.support.Order.DESC;
import static org.np.stoman.dao.support.Restrict.NOTEQ;
import static org.np.stoman.dao.support.Restrict.NULL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.np.stoman.dao.conf.HibernateUtil;
import org.np.stoman.dao.support.HibernateSupport;
import org.np.stoman.persistence.Vendors;

public class Test {

	/**
	 * @param args
	 */
	public static void main1(String[] args) {
		Session s = HibernateUtil.openSession();
		Transaction tr = s.beginTransaction();
		Vendors v = new Vendors();
		v.setName("V2");
		HibernateSupport.getInstance().setSession(s);
		HibernateSupport.getInstance().save(v);
		tr.commit();
		s.close();
		System.out.println("After save");
		Session s1 = HibernateUtil.openSession();
		HibernateSupport.getInstance().setSession(s1);
		v = HibernateSupport.getInstance().get(v.getClass()).get(0);
		System.out.println("Id" + v.getVendorId());
		s1.close();
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session s = HibernateUtil.openSession();
		HibernateSupport.getInstance().setSession(s);
		long st = System.currentTimeMillis();
		List<Vendors> vendors = HibernateSupport.getInstance().get(
				Vendors.class, DESC.order(new String[] { NAME }),
				NULL.restrict(new Object[] { ADDRESS }),
				NOTEQ.restrict(new Object[] { NAME, "V1" }));
		System.out.println(System.currentTimeMillis() - st);
		for (Vendors vendor : vendors)
			System.out.println(vendor.getName());
	}
}
