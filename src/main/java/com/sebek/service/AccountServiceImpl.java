package com.sebek.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebek.dao.AccountDao;
import com.sebek.dao.AccountRoleDao;
import com.sebek.model.Account;
import com.sebek.model.AccountRole;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Autowired
	AccountRoleDao accountRoleDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	SaltSource saltSource;

	@Override
	public Account create(Account account, AccountRole accountRole) {
		account.setPasswordSha(passwordEncoder.encodePassword(account.getPassword(), saltSource));
//		account.setPasswordSha(account.getPassword());
		account = accountDao.create(account);
		accountRoleDao.create(accountRole);
		return account;
	}

	@Override
	public Account update(Account account) {
		account.setPasswordSha(passwordEncoder.encodePassword(account.getPassword(), saltSource));
//		account.setPasswordSha(account.getPassword());
		return accountDao.update(account);
	}

	@Override
	public void delete(Account account) {
		Collection<AccountRole> accountRoles = account.getAccountRole();
		for (AccountRole accountRole : accountRoles) {
			accountRoleDao.delete(accountRole);
		}
		accountDao.delete(account);
	}

	@Override
	public Account findByName(String name) {
		return accountDao.findByName(name);
	}
	
	@Override
	public Account findByEmail(String email) {
		return accountDao.findByEmail(email);
	}

	@Override
	public Boolean checkNameOccupied(String name) {
		return accountDao.findByName(name) != null;
	}
	
	@Override
	public Boolean checkEmailRegistered(String email) {
		return accountDao.findByEmail(email) != null;
	}

	@Override
	public Collection<Account> listAccounts() {
		return accountDao.listAccounts();
	}

}