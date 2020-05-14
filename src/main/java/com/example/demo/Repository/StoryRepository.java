package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscStory;

@Repository
public interface StoryRepository extends CrudRepository<TsscStory, Long> {

}
