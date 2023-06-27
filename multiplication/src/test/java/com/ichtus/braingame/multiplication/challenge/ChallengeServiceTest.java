package com.ichtus.braingame.multiplication.challenge;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class ChallengeServiceTest {
    private ChallengeService challengeService;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl();
    }

    @Test
    public void checkCorrectAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john", 3000);
        ChallengeAttempt attempt = challengeService.verifyAttempt(attemptDTO);
        then(attempt.isCorrect()).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john", 5000);
        ChallengeAttempt attempt = challengeService.verifyAttempt(attemptDTO);
        then(attempt.isCorrect()).isFalse();
    }
}
