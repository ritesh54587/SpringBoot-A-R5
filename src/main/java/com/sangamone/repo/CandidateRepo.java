package com.sangamone.repo;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.model.Candidate;

public interface CandidateRepo extends CrudRepository<Candidate, Integer> {

}
