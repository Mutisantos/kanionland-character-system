package com.kanionland.charsheet.bff.infrastructure.clients;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.kanionland.charsheet.bff.WireMockTestConfig;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@EnableFeignClients
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockTestConfig.class})
class CharactersClientTest {

  @Autowired
  private WireMockServer characterClientServer;

  @Autowired
  private WireMockServer characterClientServerBack;

  @Autowired
  private CharactersClient characterClient;

  @SneakyThrows
  private static void setupCharacterClientResponse(WireMockServer mockService) {
    final String responseBody = """
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

    mockService.stubFor(WireMock.get(WireMock.urlPathEqualTo("/characters"))
        .withQueryParam("offset", equalTo("10"))
        .withQueryParam("page_size", equalTo("10"))
        .withQueryParam("page_number", equalTo("10"))
        .willReturn(WireMock.aResponse()
            .withStatus(HttpStatus.OK.value())
            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .withBody(responseBody)));
  }

  @BeforeEach
  @SneakyThrows
  void setUp() {
    setupCharacterClientResponse(characterClientServer);
    setupCharacterClientResponse(characterClientServerBack);
  }

  @Test
  void whenGetCharacters_thenReturnCharacterList() {
    // When
    var result = characterClient.getCharacters("authToken", 10, 10, 10);
    // Then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    var character = result.get(0);
    assertThat(character.getCharId()).isEqualTo("550e8400-e29b-41d4-a716-446655440000");
    assertThat(character.getName()).isEqualTo("Mayu");
    assertThat(character.getRace()).isEqualTo("Pumiblu");
    assertThat(character.getGender()).isEqualTo("Female");
    assertThat(character.getAge()).isEqualTo(25);
    assertThat(character.getWeight()).isEqualTo(70);
    assertThat(character.getHeight()).isEqualTo(180);
    assertThat(character.getMoney()).isEqualTo(1000);
    assertThat(character.getTitle()).isEqualTo("Hunter");
    assertThat(character.getHunger()).isEqualTo(50);
    assertThat(character.getThirst()).isEqualTo(50);
    assertThat(character.getSleep()).isEqualTo(50);
  }


}
