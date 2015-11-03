package com.sebek.validator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sebek.model.Account;
import com.sebek.service.AccountService;

public class AccountValidator implements Validator {

	@Autowired
	private AccountService accountService;

	@Override
	public boolean supports(Class clazz) {
		// just validate the Account instances
		return Account.class.isAssignableFrom(clazz);
	}

	Authentication auth;
	String userName;

	@Override
	public void validate(Object target, Errors errors) {

		auth = SecurityContextHolder.getContext().getAuthentication();
		userName = auth.getName();

		Account account = (Account) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "account.required.name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "account.required.email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "account.required.password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "account.required.confirmPassword", "required");

		rejectIfNameOccupied(account, errors);
		rejectIfEmailIsBad(account, errors);
		rejectIfEmailRegistered(account, errors);
		rejectIfPasswordNotEquals(account, errors);
	}

	private void rejectIfNameOccupied(Account account, Errors errors) {
		if (userName == "anonymousUser" && accountService.checkNameOccupied(account.getName())) {
			errors.rejectValue("name", "account.required.name.reserved");
		}
	}

	private void rejectIfEmailIsBad(Account account, Errors errors) {
		EmailValidator emailValidator = new EmailValidator();
		
//		Logger logger = Logger.getLogger("AccountValidator");
//		logger.log(Level.INFO, ":" + account.getEmail().length());
		
		if (account.getEmail().length() > 0  && !emailValidator.validate(account.getEmail())) {
			errors.rejectValue("email", "account.incorrect.email");
		}
	}

	private void rejectIfEmailRegistered(Account account, Errors errors) {
		if (userName == "anonymousUser" && accountService.checkEmailRegistered(account.getEmail())) {
			errors.rejectValue("email", "account.required.email.registered");
		}
	}

	private void rejectIfPasswordNotEquals(Account account, Errors errors) {
		if (!(account.getPassword().equals(account.getConfirmPassword()))) {
			errors.rejectValue("password", "account.notmatch.password");
		}
	}

}