package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscTimeInterval;


@Repository
public interface TimeIntervalRepository extends CrudRepository<TsscTimeInterval, Long> {

}
