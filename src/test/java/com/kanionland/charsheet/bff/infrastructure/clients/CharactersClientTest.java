package com.kanionland.charsheet.bff.infrastructure.clients;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.kanionland.charsheet.bff.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@EnableFeignClients(clients = CharactersClient.class)
@Import(TestConfig.class)
class CharactersClientTest {

  public static final String INCOMING_UUID = "550e8400-e29b-41d4-a716-446655440000";

  @Autowired
  private CharactersClient characterClient;

  private WireMockServer wireMockServer;

  @BeforeEach
  void setUp() {
    wireMockServer = new WireMockServer(8089);
    wireMockServer.start();
    WireMock.configureFor("localhost", 8089);
  }

  @AfterEach
  void tearDown() {
    wireMockServer.stop();
  }

  @Test
  void getCharacters_shouldReturnCharacters() {
    // Given
    String responseBody = """
            [{
                "char_id":"550e8400-e29b-41d4-a716-446655440000",
                "name":"Mayu",
                "race":"Pumiblu",
                "gender":"Female",
                "age":25,
                "weight":70,
                "height":180,
                "money":1000,
                "title":"Hunter",
                "hunger":50,
                "thirst":50,
                "sleep":50
            }]""";

    stubFor(get(urlPathEqualTo("/characters"))
        .withQueryParam("offset", equalTo("10"))
        .withQueryParam("page_size", equalTo("10"))
        .withQueryParam("page_number", equalTo("10"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(responseBody)));

    // When
    var result = characterClient.getCharacters(10, 10, 10);

    // Then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    var character = result.get(0);
    assertEquals(INCOMING_UUID, character.getCharId());
    assertEquals("Mayu", character.getName());
    assertEquals("Pumiblu", character.getRace());
    assertEquals("Female", character.getGender());
    assertEquals(25, character.getAge());
    assertEquals(70, character.getWeight());
    assertEquals(180, character.getHeight());
    assertEquals(1000, character.getMoney());
    assertEquals("Hunter", character.getTitle());
    assertEquals(50, character.getHunger());
    assertEquals(50, character.getThirst());
    assertEquals(50, character.getSleep());
  }
}
