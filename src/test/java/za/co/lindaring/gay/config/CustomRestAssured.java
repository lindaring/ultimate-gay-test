package za.co.lindaring.gay.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Rule;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

public class CustomRestAssured {

    public RequestSpecification spec;
    public RestDocumentationFilter filter;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    public void setUp() {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
        this.filter = RestAssuredRestDocumentation.document("{methodName}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
        );
    }

}
