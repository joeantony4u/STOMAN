package org.np.stoman.dao.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public enum Restrict {

	EQ("EQ"), NOTEQ("NOTEQ"), GE("GE"), LE("LE"), BETWEEN("BETWEEN"), GT("GT"), LT(
			"LT"), IN("IN"), NOTIN("NOTIN"), AND("AND"), OR("OR"), NULL("NULL"), NOTNULL(
			"NOTNULL");

	Restrict(String type) {
		this.type = type;
	}

	private String type;

	@SuppressWarnings("unchecked")
	public List<Criterion> restrict(Object[]... values) {

		List<Criterion> criterions = new ArrayList<Criterion>();
		switch (valueOf(type)) {
		case IN:
			for (Object[] value : values)
				criterions.add(Restrictions.in((String) value[0],
						(Collection) value[1]));
			break;
		case NOTIN:
			for (Object[] value : values)
				criterions.add(Restrictions.not(Restrictions.in(
						(String) value[0], (Collection) value[1])));
			break;
		case EQ:
			for (Object[] value : values)
				criterions.add(Restrictions.eq((String) value[0], value[1]));
			break;
		case NOTEQ:
			for (Object[] value : values)
				criterions.add(Restrictions.ne((String) value[0], value[1]));
			break;
		case GE:
			for (Object[] value : values)
				criterions.add(Restrictions.ge((String) value[0], value[1]));
			break;
		case LE:
			for (Object[] value : values)
				criterions.add(Restrictions.le((String) value[0], value[1]));
			break;
		case BETWEEN:
			for (Object[] value : values)
				criterions.add(Restrictions.between((String) value[0],
						value[1], value[2]));
			break;
		case GT:
			for (Object[] value : values)
				criterions.add(Restrictions.gt((String) value[0], value[1]));
			break;
		case LT:
			for (Object[] value : values)
				criterions.add(Restrictions.lt((String) value[0], value[1]));
			break;
		case AND:
			for (Object[] value : values)
				criterions.add(Restrictions.and((Criterion) value[0],
						(Criterion) value[1]));
			break;
		case OR:
			for (Object[] value : values)
				criterions.add(Restrictions.or((Criterion) value[0],
						(Criterion) value[1]));
			break;
		case NULL:
			for (Object[] value : values)
				criterions.add(Restrictions.isNull((String) value[0]));
			break;
		case NOTNULL:
			for (Object[] value : values)
				criterions.add(Restrictions.isNotNull((String) value[0]));
			break;

		}
		return criterions;
	}
}
