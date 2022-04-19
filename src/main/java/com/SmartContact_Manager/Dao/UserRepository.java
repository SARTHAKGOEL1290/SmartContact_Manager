package com.SmartContact_Manager.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.SmartContact_Manager.Entities.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{
	
	@Query("select u from Users u where u.email= :email")
	public Users getUserByUserName(@Param("email") String email );

}
