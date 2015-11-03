package com.sebek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebek.dao.TestDaoImpl;
import com.sebek.model.Test;

@Transactional
@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired
	TestDaoImpl testDao;
	
	@Override
	public Test persist(Test test)
	{
		return testDao.persist(test);
	}
}
