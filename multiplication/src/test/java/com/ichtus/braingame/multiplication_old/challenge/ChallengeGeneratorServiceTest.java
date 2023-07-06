package com.ichtus.braingame.multiplication_old.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class ChallengeGeneratorServiceTest {
    private ChallengeGeneratorService challengeGeneratorService;

    @Spy // по умолчанию выполняется оригинальная логика метода, но тоже можно переопределить поведение
    private Random random;

    @BeforeEach
    public void setUp() {
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() {

        // когда вызываем метод создания рандомного числа, первый раз верни 20, потом 30
        // генерим от 0 до 89, что эквивалентно от 11 до 100, нужно только потом прибавить 11
        given(random.nextInt(89)).willReturn(20, 30);

        // создаем новый челендж, он обратится к рандомному генератору и получит 20 и 30
        // добавим к ним 11 и вернем из метода 31 и 41
        Challenge challenge = challengeGeneratorService.randomChallenge();

        // проверяем что ответ соответствует 31 и 41
        then(challenge).isEqualTo(new Challenge(31, 41));
    }
}
