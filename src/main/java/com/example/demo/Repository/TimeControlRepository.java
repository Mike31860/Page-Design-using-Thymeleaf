package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscTimecontrol;

@Repository
public interface TimeControlRepository extends CrudRepository<TsscTimecontrol, Long> {

}
