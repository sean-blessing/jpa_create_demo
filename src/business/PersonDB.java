package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class PersonDB {
	public static ArrayList<Person> getAllPersons() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p",Person.class);
			ArrayList<Person> allPersons = new ArrayList<Person>(query.getResultList());
			return allPersons;
		}
		finally {
			em.close();
		}
	}
	
	public static Person getPersonById(int userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Person user = em.find(Person.class, userId);
			return user;
		}
		finally {
			em.close();
		}
	}

	public static boolean addPerson(Person u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(u);
			//em.flush();
			et.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}

	public static boolean deletePerson(Person u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.remove(em.merge(u));
			et.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}

	public static boolean updatePerson(Person u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.merge(u);
			et.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}
}
