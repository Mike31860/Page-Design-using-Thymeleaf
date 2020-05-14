package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscTopic;

@Repository
public interface TopicRepository extends CrudRepository<TsscTopic, Long > {

}
