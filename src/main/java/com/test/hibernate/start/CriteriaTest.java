package com.test.hibernate.start;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.test.hibernate.entities.Author;
import com.test.hibernate.objects.HibernateUtil;

public class CriteriaTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Author> authors = null;

		try {

			session.beginTransaction();

			Criteria criteria = session.createCriteria(Author.class);
			// criteria.add(Restrictions.eq("id", 2L));
			// criteria.add(Restrictions.like("name", "head", MatchMode.END));
			// criteria.add(Restrictions.between("id", 2L, 4L));
			// criteria.add(Restrictions.sqlRestriction("id > 2"));
			criteria.addOrder(Order.desc("id"));

			authors = criteria.list();

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}

		authors.stream().forEach(System.out::println);

	}

}
