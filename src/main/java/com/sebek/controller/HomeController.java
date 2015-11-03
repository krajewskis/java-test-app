package com.sebek.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class HomeController
{
 
	@RequestMapping(value="/", 
			method = RequestMethod.GET)
	public String getHome(ModelMap model, Principal principal)
	{
		String name;
		
		//#1
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		name = auth.getName();
		
		//#2
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		name = user.getUsername();
		
		//#3
		name = principal.getName();
	      
		model.addAttribute("username", name);
		return "home";
	}
 }