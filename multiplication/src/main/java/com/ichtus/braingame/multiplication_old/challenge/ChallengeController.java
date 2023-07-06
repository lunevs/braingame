package com.ichtus.braingame.multiplication_old.challenge;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/challenges")
public class ChallengeController {
    private final ChallengeGeneratorService generatorService;

    @GetMapping("/random")
    Challenge getRandomChallenge() {
        Challenge challenge = generatorService.randomChallenge();
        log.info("Generating random challenge = {}", challenge);
        return challenge;
    }
}
