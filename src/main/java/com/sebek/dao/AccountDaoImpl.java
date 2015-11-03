package com.sebek.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sebek.model.Account;
import com.sebek.dao.AbstractDao;

@Repository("accountDao")
public class AccountDaoImpl extends AbstractDao<Account, Id> implements AccountDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Collection<Account> listAccounts() {
		List<Account> accounts = entityManager.createQuery("SELECT a FROM Account a JOIN a.accountRole r").getResultList();
		return accounts;
	}

	private Account findBy(String property, String value) {
		List<Account> resultList = entityManager.createQuery("SELECT a FROM Account a JOIN a.accountRole r WHERE acc_" + property + " = :" + property)
				.setParameter(property, value).getResultList();
		return resultList.size() == 1 ? resultList.get(0) : null;
	}

	@Override
	public Account findByName(String name) {
		return findBy("name", name);
	}

	@Override
	public Account findByEmail(String email) {
		return findBy("email", email);
	}
}