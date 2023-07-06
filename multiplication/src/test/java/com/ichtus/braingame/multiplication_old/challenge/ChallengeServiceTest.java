package com.ichtus.braingame.multiplication_old.challenge;


import com.ichtus.braingame.multiplication_old.user.User;
import com.ichtus.braingame.multiplication_old.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class ChallengeServiceTest {
    private ChallengeService challengeService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChallengeAttemptRepository attemptRepository;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(userRepository, attemptRepository);
        given(attemptRepository.save(any())).will(returnsFirstArg());
    }

    @Test
    public void checkCorrectAttemptTest() {
        String userAlias = "john";

        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, userAlias, 3000);
        ChallengeAttempt attempt = challengeService.verifyAttempt(attemptDTO);
        then(attempt.isCorrect()).isTrue();

        verify(userRepository).save(new User(null, userAlias, null));
        verify(attemptRepository).save(attempt);
    }

    @Test
    public void checkExistingUserTest() {
        // given
        User existingUser = new User(1L, "john", null);
        given(userRepository.findByAlias(existingUser.getAlias()))
                .willReturn(Optional.of(existingUser));
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(30, 40, existingUser.getAlias(), 1200);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();
        //then(resultAttempt.getUser()).isEqualTo(existingUser);

        verify(userRepository, never()).save(any());
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "john", 5000);
        ChallengeAttempt attempt = challengeService.verifyAttempt(attemptDTO);
        then(attempt.isCorrect()).isFalse();
    }

    @Test
    public void checkGetLastAttempts() {

    }
}
