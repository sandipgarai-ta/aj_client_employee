package com.mib.users.service;

import java.util.List;

import com.mib.users.models.Path;
import com.mib.users.models.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<Path> findPaths();
	public List<User> getAll();
}
