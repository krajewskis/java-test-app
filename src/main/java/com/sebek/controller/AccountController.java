package com.sebek.controller;

import java.io.IOException;
import java.util.Collection;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sebek.model.Account;
import com.sebek.model.AccountRole;
import com.sebek.service.AccountService;
import com.sebek.service.MailService;
import com.sebek.validator.AccountValidator;

@Controller
public class AccountController {

	@Autowired
	AccountValidator accountValidator;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String getLoginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogout(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getAccount(ModelMap model, Account account) {
		model.addAttribute("account", account);
		return "account";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postAccount(@ModelAttribute("account") Account account, BindingResult result, SessionStatus status) throws AddressException, IOException, MessagingException {

		accountValidator.validate(account, result);

		if (result.hasErrors()) {
			return "account";
		} else {
			AccountRole accountRole = new AccountRole(AccountRole.ROLE_USER);
			accountRole.setAccount(account);
			account.getAccountRole().add(accountRole);
			accountService.create(account, accountRole);
			MailService.Send(account.getEmail(), "registration confirmation", "registration confirmation");
			status.setComplete();
			return "accountSuccess";
		}
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String getForgot(ModelMap model, Account account) {
		model.addAttribute("account", account);
		return "forgot";
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String postForgot(@ModelAttribute("account") Account account, BindingResult result, SessionStatus status) {

		accountValidator.validate(account, result);

		if (result.hasErrors()) {
			return "forgot";
		} else {
			//TODO make new password
			status.setComplete();
			return "forgotSuccess";
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView("users");
		Collection<Account> users = accountService.listAccounts();
		mav.addObject("users", users);
		return mav;
	}

	@RequestMapping(value = "/users/edit/{name}", method = RequestMethod.GET)
	public String getUserEdit(@PathVariable("name") String name, ModelMap model) {
		Account account = accountService.findByName(name);
		return getAccount(model, account);
	}

	@RequestMapping(value = "/users/edit/{name}", method = RequestMethod.POST)
	public String getUserEditPost(@PathVariable("name") String name, @ModelAttribute("account") Account account, BindingResult result, SessionStatus status) {

		accountValidator.validate(account, result);

		if (result.hasErrors()) {
			return "account";
		} else {
			Account currentAccount = accountService.findByName(name);
			currentAccount.setPassword(account.getPassword());
			currentAccount.setConfirmPassword(account.getConfirmPassword());
			accountService.update(currentAccount);
			account.setPasswordSha(currentAccount.getPasswordSha());
			status.setComplete();
			return "accountSuccess";
		}
	}

	@RequestMapping(value = "/users/remove/{name}", method = RequestMethod.GET)
	public ModelAndView getUserRemove(@PathVariable("name") String name) {
		Account account = accountService.findByName(name);
		accountService.delete(account);
		return getUsers();
	}
}