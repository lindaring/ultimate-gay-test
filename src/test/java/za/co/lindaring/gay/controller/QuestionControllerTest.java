package za.co.lindaring.gay.controller;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.lindaring.gay.config.CustomRestAssured;
import za.co.lindaring.gay.repo.QuestionRepo;
import za.co.lindaring.gay.repo.model.Question;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
@Slf4j
public class QuestionControllerTest extends CustomRestAssured {

	@LocalServerPort
	public void intPort(final int port) {
		RestAssured.port = port;
	}

	@Before
	public void setUp() {
		super.setUp();
	}

	@Mock
	private QuestionRepo questionRepo;

	@Test
	public void get_Definition_Test_Success() {
		List<Question> questionList = new ArrayList<>();
		questionList.add(new Question(1, "Question 1 desc", "location/1",
				1, "Answer 1 desc", "location/2"));
		Mockito.doReturn(questionList).when(questionRepo).findQuestions(10);

		given(spec)
			.filter(
				filter.document(
					httpRequest(),
					httpResponse(),
					curlRequest(),
					relaxedResponseFields(
						fieldWithPath("questionsList[]").description("List of questions."),
						fieldWithPath("questionsList[].id").description("The unique question identifier."),
						fieldWithPath("questionsList[].desc").description("The question."),
						fieldWithPath("questionsList[].pic").description("The background (picture) of the question."),
						fieldWithPath("questionsList[].answersList[]").description("The list of possible answer."),
						fieldWithPath("questionsList[].answersList[].id").description("The unique answers identifier."),
						fieldWithPath("questionsList[].answersList[].desc").description("The answer."),
						fieldWithPath("questionsList[].answersList[].pic").description("The background (picture) of the answer.")
				)
			))
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
			.get("/ultimate-gay-test/v1/question/")
			.then()
			.log().all()
			.assertThat()
			.body("questionsList[0].id", Matchers.notNullValue())
			.and()
			.body("questionsList[0].desc", Matchers.notNullValue())
			.and()
			.statusCode(200);
	}

}
