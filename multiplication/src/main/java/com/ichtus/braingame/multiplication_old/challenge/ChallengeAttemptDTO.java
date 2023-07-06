package com.ichtus.braingame.multiplication_old.challenge;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class ChallengeAttemptDTO {

    @Min(1) @Max(99)
    int factorA, factorB;

    @NotBlank
    String alias;

    @Positive
    int guess;
}
