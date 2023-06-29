package com.ichtus.braingame.multiplication.challenge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {
    /**
     * @return the last 10 attempts for a given user, identified by their alias.
     * */
    @Query("SELECT a FROM challenge_attempt a WHERE a.user_id = ?1 ORDER BY a.id DESC")
    List<ChallengeAttempt> findTop10ByUserIdOrderByIdDesc(Long userId);

//    /**
//     * @return the last attempts for a given user, identified by their alias.
//     * */
//    @Query("SELECT a FROM ChallengeAttempt a WHERE a.user_id = ?1 ORDER BY a.id DESC")
//    List<ChallengeAttempt> lastAttempts(Long userId);

}
