package com.teste.restassured;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

@TestInstance(Lifecycle.PER_CLASS)
public class GetPokemonNameTest {
   @BeforeAll
   public void setup() {
      RestAssured.baseURI = "https://pokeapi.co/api/v2/";
   }

   @Test
   public void GetPokemonNameSuccess() {
      ExtractableResponse<Response> pokemon = given().get("pokemon/bulbasaur").then().statusCode(200).extract();
      String statusCodeResponse = pokemon.response().getStatusLine();
      String pokemonName = pokemon.path("name");
      assertEquals("HTTP/1.1 200 OK", statusCodeResponse);
      assertEquals("bulbasaur", pokemonName);
   }

   @Test
   public void GetPokemonNameFailureStatusCode() {
      String statusCodeResponse = given().get("pokemon/123abc").then().statusCode(404).extract().asString();
      assertEquals("Not Found", statusCodeResponse);
   }
}
