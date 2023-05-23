package com.sangamone.repo;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.model.Vote;

public interface VoteRepo extends CrudRepository<Vote, Integer> {

}
