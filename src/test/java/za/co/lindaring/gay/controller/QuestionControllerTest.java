package za.co.lindaring.gay.controller;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.lindaring.gay.config.CustomRestAssured;

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

	@Test
	public void get_Definition_Test_Success() {
		given(spec)
			.filter(
				filter.document(
					httpRequest(),
					httpResponse(),
					curlRequest(),
					relaxedResponseFields(
							fieldWithPath("id").description("The unique question identifier."),
							fieldWithPath("desc").description("The question."),
							fieldWithPath("background").description("The background (picture) of the question."),
							fieldWithPath("answer[]").description("The list of possible answer."),
							fieldWithPath("answer[].id").description("The unique answers identifier."),
							fieldWithPath("answer[].desc").description("The answer."),
							fieldWithPath("answer[].background").description("he background (picture) of the answer.")
				)
			))
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
			.get("/ultimate-gay-test/v1/question/")
			.then()
			.log().all()
			.assertThat()
			.body("desc", Matchers.notNullValue())
			.and()
			.body("background", Matchers.notNullValue())
			.and()
			.body("answer", Matchers.notNullValue())
			.and()
			.statusCode(200);
	}

}
