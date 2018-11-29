package com.mib.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mib.users.models.User;
import com.mib.users.repository.UserRepository;
import com.mib.users.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService us;
	
	@PostMapping
	public @ResponseBody int registerUser(@RequestBody User user) {
		us.saveUser(user);
		return 1;
	}
}
