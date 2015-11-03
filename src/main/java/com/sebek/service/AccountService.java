package com.sebek.service;

import java.util.Collection;
import java.util.List;

import com.sebek.model.Account;
import com.sebek.model.AccountRole;

public interface AccountService {

	public Account create(Account account, AccountRole accountRole);

	public Account update(Account account);

	public void delete(Account account);

	public Account findByName(String name);
	
	public Account findByEmail(String email);

	public Boolean checkNameOccupied(String name);
	
	public Boolean checkEmailRegistered(String name);

	public Collection<Account> listAccounts();

}
