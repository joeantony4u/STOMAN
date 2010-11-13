package org.np.stoman.dao.support;

import java.util.ArrayList;
import java.util.List;

public enum Order {
	ASC("ASC"), DESC("DESC");

	Order(String type) {
		this.type = type;
	}

	private String type;

	public List<org.hibernate.criterion.Order> order(String... values) {
		List<org.hibernate.criterion.Order> orders = new ArrayList<org.hibernate.criterion.Order>();
		switch (valueOf(type)) {
		case ASC:
			for (String value : values)
				orders.add(org.hibernate.criterion.Order.asc(value));
			break;
		case DESC:
			for (String value : values)
				orders.add(org.hibernate.criterion.Order.desc(value));
			break;
		}
		return orders;
	}
}
