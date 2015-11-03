package com.sebek;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sebek.model.Account;
import com.sebek.model.AccountRole;
import com.sebek.service.AccountService;

public class AccountApp {

	private static final String ACCOUNT_NAME = "admin";
	private static final String ACCOUNT_EMAIL = "admin@admin.com";
	private static final String ACCOUNT_PASSWORD = "admin";

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test.xml");
		AccountService accountService = applicationContext.getBean("accountService", AccountService.class);

		Account account;

		account = accountService.findByName(ACCOUNT_NAME);

		if (account != null) {
			accountService.delete(account);
		}

		account = new Account();

		account.setName(ACCOUNT_NAME);
		account.setEmail(ACCOUNT_EMAIL);
		account.setPassword(ACCOUNT_PASSWORD);
		account.setConfirmPassword(ACCOUNT_PASSWORD);

		AccountRole accountRole = new AccountRole();
		accountRole.setRole(AccountRole.ROLE_ADMIN);
		accountRole.setAccount(account);
		account.getAccountRole().add(accountRole);

		accountService.create(account, accountRole);

		// Collection<Account> accounts = accountService.listAccounts();
		// for (Account account : accounts) {
		// System.out.println(account.getName());
		// }

		System.out.println("Done.");

	}

}
