package com.malves.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.malves.parking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase {
	
	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
//		System.out.println(randomPort);
		RestAssured.port = randomPort;
	}

	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
//			.auth()
//			.basic("user", "malves@098")
			.header("Authorization", "Basic dXNlcjptYWx2ZXNAMDk4")
			.when()
			.get("/parkings")
			.then()
			.statusCode(HttpStatus.OK.value());
//			.body("license[0]", Matchers.equalTo("WRT-5555"));
//			.extract().response().body().prettyPrint();
	}
	
	@Test
	void whenCreateThenCheckIsCreated() {
		ParkingCreateDTO createDTO = new ParkingCreateDTO();
		createDTO.setColor("AMARELO");
		createDTO.setLicense("WRT-5555");
		createDTO.setModel("BRASILIA");
		createDTO.setState("SP");
		
		RestAssured.given()
			.when()
			.auth()
			.basic("user", "malves@098")
//			.header("Authorization", "Basic dXNlcjptYWx2ZXNAMDk4")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO)
			.post("/parkings")
			.then()
			.statusCode(HttpStatus.CREATED.value())
			.body("license", Matchers.equalTo("WRT-5555"))
			.body("color", Matchers.equalTo("AMARELO"));
	}
		
}
