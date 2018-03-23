package com.test.hibernate.start;

import java.util.List;

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
		List<Author> authors = null;

		try {

			session.beginTransaction();

			Author firstAuthor = (Author) session.get(Author.class, 1L);
			System.out.println(firstAuthor);

			// authors = selectAuthorsQuery.list();
			// authors.stream().forEach(System.out::println);

			SQLQuery queryUpdate = session.createSQLQuery("update authors set name = ? where id = ?");
			queryUpdate.setParameter(0, "Atoms For Peace");
			queryUpdate.setParameter(1, "2");
			queryUpdate.executeUpdate();

			authors = selectAuthorsQuery.list();

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
			sessionFactory.close();
		}

		authors.stream().forEach(System.out::println);
	}

}
