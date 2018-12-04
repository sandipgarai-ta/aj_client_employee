package com.mib.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mib.users.models.User;
import com.mib.users.repository.UserRepository;
import com.mib.users.service.UserService;
import com.mib.users.utils.ResponseDetails;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService us;
	
	@Autowired
	BCryptPasswordEncoder bcpe;
	
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<?> loginUser(@RequestBody User user) {
		User u = us.findUserByEmail(user.getEmail());
		if(u == null) {
			return new ResponseEntity<ResponseDetails>(new ResponseDetails("Sorry Email not registered.",0), HttpStatus.FORBIDDEN);
		}
		if(bcpe.matches(user.getPassword(), u.getPassword())) {
			u.setPassword("");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		} else {
			return new ResponseEntity<ResponseDetails>(new ResponseDetails("Looks like you have enterd wrong password.",0), HttpStatus.FORBIDDEN);
		}
		//return new ResponseEntity<ResponseDetails>(new ResponseDetails("Looks like you have enterd wrong password.",0), HttpStatus.FORBIDDEN);
		//return us.saveUser(user);
	}
	
	@PostMapping
	public @ResponseBody User registerUser(@RequestBody User user) {
		return us.saveUser(user);
	}
}
