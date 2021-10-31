package com.teste.restassured;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;;

@TestInstance(Lifecycle.PER_CLASS)
public class GetPokemonNameTest {
   @BeforeAll
   public void setup() {
      RestAssured.baseURI = "https://pokeapi.co/api/v2/";
   }

   @Test
   public void GetPokemonNameSuccess() {
      String pokemonName = given().get("pokemon/bulbasaur").then().statusCode(200).extract().path("name");
      assertEquals("bulbasaur", pokemonName);
   }

   @Test
   public void GetPokemonNameFailureStatusCode() {
      given().get("pokemon/123abc").then().statusCode(404);
   }
}
