package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.TsscGame;

@Repository
public interface GameRepository extends CrudRepository<TsscGame, Long> {

}
