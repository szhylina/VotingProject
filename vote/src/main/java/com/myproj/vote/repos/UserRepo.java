package com.myproj.vote.repos;

import org.springframework.data.repository.CrudRepository;

import com.myproj.vote.domain.User;

public interface UserRepo extends CrudRepository<User, Integer>{
	User findByUsername(String username);
}
