package com.ichtus.braingame.gamification_old.challenge;

import lombok.Value;

@Value
public class ChallengeSolvedDTO {

    long attemptId;
    boolean correct;
    int factorA;
    int factorB;
    long userId;
    String userAlias;

}
