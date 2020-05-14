package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscSprint;

@Repository
public interface SprintRepository extends CrudRepository<TsscSprint, Long>{

}
