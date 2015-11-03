package com.sebek.dao;

import java.util.Collection;

import com.sebek.model.Account;

public interface AccountDao {

	public Account create(Account account);
	
	public Account update(Account account);
	
	public void delete(Account account);
	
	public Account findByName(String name);
	
	public Account findByEmail(String email);
	
	public Collection<Account> listAccounts();

}
