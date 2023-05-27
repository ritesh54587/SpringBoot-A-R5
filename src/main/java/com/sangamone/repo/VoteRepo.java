package com.sangamone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sangamone.model.Vote;

public interface VoteRepo extends CrudRepository<Vote, Integer> {
   @Query(value="select * from vote where seat_id=?1", nativeQuery=true)
	List<Vote> getCandidateVotesBySeatId(int seat_id);

   

   @Query(value = "SELECT CASE WHEN Congress > BJP AND Congress > JDS AND Congress > Others THEN 'Congress' " +
           "WHEN BJP > Congress AND BJP > JDS AND BJP > Others THEN 'BJP' " +
           "WHEN JDS > Congress AND JDS > BJP AND JDS > Others THEN 'JDS' ELSE 'Others' END " +
           "AS Winner FROM vote WHERE seat_no = ?1", nativeQuery = true)
       String getWinnerNameBySeatNo(String seatNo);


@Query(value="select * from vote where seat_id=?1", nativeQuery=true)

Vote findPartyVotes(int seat_id);


}
