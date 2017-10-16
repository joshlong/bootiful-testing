package org.springframework.cloud.contract.verifier.tests;

import com.example.reservationservice.BaseClass;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import com.jayway.restassured.response.ResponseOptions;
import org.junit.Test;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

public class ContractVerifierTest extends BaseClass {

	@Test
	public void validate_shouldReturnAllReservations() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/reservations");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo(2);
			assertThatJson(parsedJson).array().contains("['reservationName']").isEqualTo("Srinivas");
			assertThatJson(parsedJson).array().contains("['id']").isEqualTo(1);
			assertThatJson(parsedJson).array().contains("['reservationName']").isEqualTo("Janet");
	}

}
