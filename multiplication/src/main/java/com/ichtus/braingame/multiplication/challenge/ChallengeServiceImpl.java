package com.ichtus.braingame.multiplication.challenge;

import com.ichtus.braingame.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        User user = new User(null, attemptDTO.getAlias());

        ChallengeAttempt attempt = new ChallengeAttempt(null, user.getId(),
                attemptDTO.getFactorA(), attemptDTO.getFactorB(), attemptDTO.getGuess(), isCorrect);

        return attempt;
    }
}
