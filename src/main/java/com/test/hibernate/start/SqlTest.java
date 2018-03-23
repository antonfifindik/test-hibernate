package com.test.hibernate.start;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.test.hibernate.entities.Author;
import com.test.hibernate.objects.HibernateUtil;

public class SqlTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		SQLQuery selectAuthorsQuery = session.createSQLQuery("select * from authors").addEntity(Author.class);

		try {

			session.beginTransaction();

			Author firstAuthor = (Author) session.get(Author.class, 1L);
			System.out.println(firstAuthor);

			selectAuthorsQuery.list().stream().forEach(System.out::println);

			SQLQuery queryUpdate = session.createSQLQuery("update authors set name = ? where id = ?");
			queryUpdate.setParameter(0, "Atoms For Peace");
			queryUpdate.setParameter(1, "2");
			queryUpdate.executeUpdate();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
