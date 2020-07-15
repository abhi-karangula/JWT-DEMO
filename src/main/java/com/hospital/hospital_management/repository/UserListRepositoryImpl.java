package com.hospital.hospital_management.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_management.bean.Login;

@Repository
public class UserListRepositoryImpl implements IUserListRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean isUserExists(Login login) {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Login> crquery = cb.createQuery(Login.class);
		Root<Login> root = crquery.from(Login.class);

		Predicate userNamePred = cb.equal(root.get("userName"), login.getUserName());
		Predicate pwdPred = cb.like(root.get("password"), login.getPassword());
		crquery.select(root).where(cb.and(userNamePred, pwdPred));

		Query<Login> query = session.createQuery(crquery);
		List<Login> results = query.getResultList();
		return !results.isEmpty();
	}

	@Override
	public Login save(Login login) {
		Session session = entityManager.unwrap(Session.class);
		session.save(login);
		return login;
	}

	@Override
	public Login loadUsersByUsername(String userName) {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Login> crquery = cb.createQuery(Login.class);
		Root<Login> root = crquery.from(Login.class);

		Predicate userNamePred = cb.equal(root.get("userName"), userName);
		crquery.select(root).where(userNamePred);

		Query<Login> query = session.createQuery(crquery);
		Login result = query.getSingleResult();
		return result;
	}

	@Override
	public List<Login> getAllUsers() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Login> crquery = cb.createQuery(Login.class);
		Root<Login> root = crquery.from(Login.class);
		
		crquery.select(root);
		Query<Login> query = session.createQuery(crquery);
		List<Login> results = query.getResultList();
		return results;
	}

}
