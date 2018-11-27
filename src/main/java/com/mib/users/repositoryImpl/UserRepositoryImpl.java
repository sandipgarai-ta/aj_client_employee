package com.mib.users.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mib.users.models.User;
import com.mib.users.repository.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public int register(User user) {
		em.persist(user);
		return 0;
	}

}
