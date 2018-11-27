package com.mib.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mib.users.models.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}
