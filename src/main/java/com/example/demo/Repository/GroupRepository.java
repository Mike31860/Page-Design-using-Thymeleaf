package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscGroup;

@Repository
public interface GroupRepository extends CrudRepository<TsscGroup, Long> {

}
