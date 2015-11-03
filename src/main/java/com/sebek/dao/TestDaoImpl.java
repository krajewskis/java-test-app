package com.sebek.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sebek.model.Test;

@Repository("testDao")
public class TestDaoImpl implements TestDao {

	@PersistenceContext
	EntityManager entityManager;
	
	public Test persist(Test test)
	{
		entityManager.persist(test);
		return test;
	}
	
}
