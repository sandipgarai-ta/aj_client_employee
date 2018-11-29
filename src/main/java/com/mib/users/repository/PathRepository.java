package com.mib.users.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mib.users.models.Path;
@Repository
public interface PathRepository extends CrudRepository<Path, Integer> {
	List<Path> findAll();
}
