package com.sebek.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.sebek.model.Account;
import com.sebek.model.AccountRole;
import com.sebek.service.AccountService;

public class AccountServiceTest {

	private static ApplicationContext applicationContext;
	private static AccountService accountService;

	private static final String ACCOUNT_NAME = "test";
	private static final String ACCOUNT_EMAIL = "test@test.com";
	private static final String ACCOUNT_PASSWORD = "password";

	@BeforeClass
	public static void oneTimeSetUp() {
		applicationContext = new ClassPathXmlApplicationContext("test.xml");
		accountService = applicationContext.getBean("accountService", AccountService.class);
	}

	@Test
	public void CRUD() {

		Boolean bool;
		Account account;

		// read
		account = accountService.findByName("unknown");
		assertNull(account);

		account = accountService.findByName(null);
		assertNull(account);

		bool = accountService.checkNameOccupied("unknown");
		assertEquals(0, bool ? 1 : 0);

		bool = accountService.checkNameOccupied(null);
		assertEquals(0, bool ? 1 : 0);

		account = accountService.findByName(ACCOUNT_NAME);

		if (account != null) {
			accountService.delete(account);
		}

		bool = accountService.checkNameOccupied(ACCOUNT_NAME);
		assertEquals(0, bool ? 1 : 0);

		// create
		account = new Account();
		account.setName(ACCOUNT_NAME);
		account.setEmail(ACCOUNT_EMAIL);
		account.setPassword(ACCOUNT_PASSWORD);
		account.setConfirmPassword(ACCOUNT_PASSWORD);

		AccountRole accountRole = new AccountRole();
		accountRole.setRole(AccountRole.ROLE_ANONYMOUS);
		accountRole.setAccount(account);
		account.getAccountRole().add(accountRole);

		accountService.create(account, accountRole);

		// read name
		account = accountService.findByName(ACCOUNT_NAME);

		assertEquals(ACCOUNT_NAME, account.getName());
		assertEquals(ACCOUNT_EMAIL, account.getEmail());

		// read email
		account = accountService.findByEmail(ACCOUNT_EMAIL);

		assertEquals(ACCOUNT_NAME, account.getName());
		assertEquals(ACCOUNT_EMAIL, account.getEmail());

		// update
		account.setName(ACCOUNT_NAME);

		accountService.update(account);

		account = accountService.findByName(ACCOUNT_NAME);

		assertEquals(ACCOUNT_NAME, account.getName());

		bool = accountService.checkNameOccupied(ACCOUNT_NAME);
		assertEquals(1, bool ? 1 : 0);

		// delete
		accountService.delete(account);

		account = accountService.findByName(ACCOUNT_NAME);

		assertNull(account);

	}

}
