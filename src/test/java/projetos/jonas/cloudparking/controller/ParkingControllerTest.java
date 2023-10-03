package projetos.jonas.cloudparking.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import projetos.jonas.cloudparking.DTO.ParkingCreateDTO;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setupTest(){
        RestAssured.port =randomPort;
    }
    @Test
    void Findall() {
        RestAssured.given()
                .auth()
                .basic("user","123")
                .when()
                .get("parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void create() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");

        RestAssured.given()
                .when()
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .body(createDTO)
                .post("parking")
                .then()
                .statusCode(201)
                .body("license", Matchers.equalTo("WRT-5555"))
                .body("color", Matchers.equalTo("AMARELO"))
                .body("model", Matchers.equalTo("BRASILIA"));
    }
}