package com.nissan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.User;
@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
	@Query("from User WHERE userName = ?1 AND password = ?2")
	public User findUserByNameAndPassword(String userName,String password);



}
