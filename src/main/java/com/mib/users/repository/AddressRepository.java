package com.mib.users.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mib.users.models.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	List<Address> findAll();
}
