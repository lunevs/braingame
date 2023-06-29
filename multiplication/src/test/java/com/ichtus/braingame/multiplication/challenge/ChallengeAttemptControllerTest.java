package com.ichtus.braingame.multiplication.challenge;

import com.ichtus.braingame.multiplication.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)  // доступ к тестовому контексту обеспечивает
@AutoConfigureJsonTesters
@WebMvcTest(ChallengeAttemptController.class)  // заставляет спринг воспринимать класс как контролер presentation level и грузит только
// соответствующие конфигурации
public class ChallengeAttemptControllerTest {

    @MockBean  // заменяем bean в контексте на mock
    private ChallengeService challengeService;

    @Autowired // грузим из тестового контекста, эмулирует поведение сервера и позволяет сделать запрос
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ChallengeAttemptDTO> dtoJacksonTester;

    @Autowired
    private JacksonTester<ChallengeAttempt> attemptJacksonTester;

    @Test
    void postValidResult() throws Exception {
        String alias = "John";
        User user = new User(1L, alias);
        long attemptId = 5L;
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 70, alias, 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user.getId(), 50, 70, 3500, true);
        // когда сервис будет вызван с объектом эквивалентным attemptDTO
        // верни expectedResponse
        given(challengeService.verifyAttempt(eq(attemptDTO))).willReturn(expectedResponse);

        MockHttpServletResponse response = mockMvc.perform(post("/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoJacksonTester.write(attemptDTO).getJson())
        ).andReturn().getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(attemptJacksonTester.write(expectedResponse).getJson());
    }

    @Test
    void postInvalidResult() throws Exception {
        ChallengeAttempt expectedResponse = new ChallengeAttempt(5L, 1L,
                50, 70, 3500, true);
        MockHttpServletResponse response = mockMvc.perform(
                        post("/attempts").contentType(MediaType.APPLICATION_JSON)
                                .content(attemptJacksonTester.write(expectedResponse).getJson()))
                .andReturn().getResponse();
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
