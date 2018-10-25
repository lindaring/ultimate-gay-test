package za.co.lindaring.gay.controller;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.lindaring.gay.config.CustomRestAssured;
import za.co.lindaring.gay.model.AnswerRequest;
import za.co.lindaring.gay.model.PostAnwersRequest;
import za.co.lindaring.gay.repo.AnswerRepo;
import za.co.lindaring.gay.repo.UserRepo;
import za.co.lindaring.gay.repo.model.Answer;
import za.co.lindaring.gay.repo.model.User;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
@Slf4j
public class AnswerControllerTest extends CustomRestAssured {

    @LocalServerPort
    public void intPort(final int port) {
        RestAssured.port = port;
    }

    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private AnswerRepo answerRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void submit_Answer_Test_Success() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(1, "Lindi Lu", "", 5, 5));
        answerList.add(new Answer(2, "Nothing", "", 7, 4));

        Mockito.doReturn(answerList).when(answerRepo).findAnswers(anyList());
        Mockito.doReturn(100L).when(userRepo).addUserAndScore(any(User.class));

        List<AnswerRequest> answerRequestList = new ArrayList<>();
        answerRequestList.add(new AnswerRequest(1, 5));
        answerRequestList.add(new AnswerRequest(2, 4));


        given(spec)
            .filter(
                filter.document(
                    httpRequest(),
                    httpResponse(),
                    curlRequest(),
                    relaxedResponseFields(
                        fieldWithPath("success").description("Returns true is successful, otherwise throws and exception."),
                        fieldWithPath("id").description("The user id.")
                    )
                ))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .body(new PostAnwersRequest("Thando", answerRequestList))
            .post("/ultimate-gay-test/v1/answer/")
            .then()
            .log().all()
            .assertThat()
            .body("success", is(true))
            .and()
            .body("id", is(100))
            .statusCode(200);
    }

}
