package com.mib.users.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mib.users.models.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>,UserRepositoryCustom {
	 User findByEmail(String email);
	 List<User> findAll();
}
