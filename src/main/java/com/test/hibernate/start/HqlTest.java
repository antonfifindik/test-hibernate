package com.test.hibernate.start;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.test.hibernate.entities.Album;
import com.test.hibernate.entities.Author;
import com.test.hibernate.objects.HibernateUtil;

public class HqlTest {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		SQLQuery selectAuthorsQuery = session.createSQLQuery("select * from authors").addEntity(Author.class);
		List<Author> authors = null;
		List<Album> albums = null;

		try {

			session.beginTransaction();

			Author author = (Author) session.get(Author.class, 3L);
			author.setName("LCD Soundsystem");
			session.update(author);

			Query query = session.createQuery("FROM Author");
			authors = query.list();

			Query query2 = session.createQuery("FROM Album as a INNER JOIN FETCH a.author INNER JOIN FETCH a.genre");
			albums = query2.list();

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}

		for (Author author : authors)
			System.out.println(author.getId() + ". " + author.getName());

		System.out.println();

		albums.stream().forEach(System.out::println);
	}

}
