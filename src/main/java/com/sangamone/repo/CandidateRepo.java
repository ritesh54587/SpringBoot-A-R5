package com.sangamone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sangamone.model.Candidate;

public interface CandidateRepo extends CrudRepository<Candidate, Integer> {

	@Query(value="select seat_no from candidate", nativeQuery=true)
	List<String> findSeats();
	@Query(value = "SELECT * FROM candidate  JOIN vote  ON candidate.seat_no = vote.seat_no WHERE seat_id = ?1", nativeQuery=true)
	Candidate getCandidateNamesBySeatId(int seat_id);

}
