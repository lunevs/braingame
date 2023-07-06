package com.ichtus.braingame.multiplication_old.challenge;

import com.ichtus.braingame.multiplication_old.user.User;
import com.ichtus.braingame.multiplication_old.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository challengeAttemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {

        log.info("looking user by alias: " + attemptDTO.getAlias());
        User user = userRepository.findByAlias(attemptDTO.getAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", attemptDTO.getAlias());
                    return userRepository.save(new User(attemptDTO.getAlias()));
                });

        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        ChallengeAttempt attempt = new ChallengeAttempt(null, user,
                attemptDTO.getFactorA(), attemptDTO.getFactorB(), attemptDTO.getGuess(), isCorrect);

        ChallengeAttempt storedAttempt = challengeAttemptRepository.save(attempt);

        return storedAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(String alias) {
        User user = userRepository.findByAlias(alias)
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", alias);
                    return userRepository.save(new User(alias));
                });
        return challengeAttemptRepository.findTop10ByUserIdOrderByIdDesc(user.getId());
    }
}
