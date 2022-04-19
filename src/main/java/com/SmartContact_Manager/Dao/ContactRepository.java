package com.SmartContact_Manager.Dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.SmartContact_Manager.Entities.Contacts;

public interface ContactRepository  extends CrudRepository<Contacts,Integer>{
	
	@Query("from Contacts as c where c.user.id =:userId")
	public Page<Contacts> getContactByUser(@Param("userId") int userId , Pageable pePegable);

}
