package za.co.lindaring.gay;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
@Slf4j
public class UltimateGayTestApplicationTests {

	private RequestSpecification documentationSpec;
	private RestDocumentationFilter restDocumentationFilter;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@LocalServerPort
	public void intPort(final int port) {
		RestAssured.port = port;
	}

	@Before
	public void setUp() {
		this.documentationSpec = new RequestSpecBuilder()
				.addFilter(documentationConfiguration(restDocumentation))
				.build();
		this.restDocumentationFilter = RestAssuredRestDocumentation.document("{methodName}",
				Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
				Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
		);
	}

	@Test
	public void get_Definition_Test_Success() {
		given(this.documentationSpec)
			.filter(this.restDocumentationFilter.document(
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
