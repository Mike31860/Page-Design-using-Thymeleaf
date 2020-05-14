package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscAdmin;


@Repository
public interface AdminRepository extends CrudRepository<TsscAdmin, Long> {

	List<TsscAdmin> findByUsername(String username);
	
	
	

}
