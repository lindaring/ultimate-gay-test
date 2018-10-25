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
import za.co.lindaring.gay.model.PostAnwersRequest;
import za.co.lindaring.gay.repo.UserRepo;
import za.co.lindaring.gay.repo.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
@Slf4j
public class UserControllerTest extends CustomRestAssured {

    @LocalServerPort
    public void intPort(final int port) {
        RestAssured.port = port;
    }

    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private UserRepo userRepo;

    @Test
    public void get_User_Test_Success() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(20, "Thando", "120.0.0.1", "mozilla/firefoz", 100, new Timestamp(1539216000L)));
        Mockito.doReturn(userList).when(userRepo).findUser(anyInt());

        given(spec)
            .filter(
                filter.document(
                    httpRequest(),
                    httpResponse(),
                    curlRequest(),
                    relaxedResponseFields(
                        fieldWithPath("id").description("The user id."),
                        fieldWithPath("name").description("The user's name."),
                        fieldWithPath("score").description("The gay score. This is the percentage of how gay the user is."),
                        fieldWithPath("visited").description("The user's name")
                    )
                ))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("id", "102")
            .get("/ultimate-gay-test/v1/user/{id}")
            .then()
            .log().all()
            .assertThat()
            .body("id", is(20))
            .and()
            .body("name", is("Thando"))
            .and()
            .body("score", is(100))
            .and()
            .body("visited", is("1970-01-18"))
            .statusCode(200);
    }

    @Test
    public void get_All_Users_Test_Success() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(20, "Thando", "120.0.0.1", "mozilla/firefoz", 100, new Timestamp(1539216000L)));
        userList.add(new User(21, "Lerato", "168.0.42.1", "mozilla/firefoz", 45, new Timestamp(1538784000L)));
        Mockito.doReturn(userList).when(userRepo).findAllUsers();

        given(spec)
            .filter(
                filter.document(
                    httpRequest(),
                    httpResponse(),
                    curlRequest(),
                    relaxedResponseFields(
                        fieldWithPath("userList[].id").description("The user id."),
                        fieldWithPath("userList[].name").description("The user's name."),
                        fieldWithPath("userList[].score").description("The gay score. This is the percentage of how gay the user is."),
                        fieldWithPath("userList[].visited").description("The user's name")
                    )
                ))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .get("/ultimate-gay-test/v1/user/all")
            .then()
            .log().all()
            .assertThat()
            .body("userList[0].id", is(20))
            .and()
            .body("userList[1].id", is(21))
            .statusCode(200);
    }

}
