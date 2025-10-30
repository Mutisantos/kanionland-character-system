package com.kanionland.charsheet.bff.application.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.kanionland.charsheet.bff.domain.models.Character;
import com.kanionland.charsheet.bff.infrastructure.responses.CharacterResponse;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class CharacterMapperTest {

  private static final String CHARACTER_NAME = "Mayu";
  private static final UUID CHAR_UUID = UUID.randomUUID();
  private static final String RACE = "Pumiblu";
  private static final String GENDER = "Female";
  private static final int AGE = 25;
  private static final int WEIGHT = 70;
  private static final int HEIGHT = 180;
  private static final int MONEY = 1000;
  private static final String TITLE = "Hunter";
  private static final int HUNGER = 50;
  private static final int THIRST = 50;
  private static final int SLEEP = 50;

  private final CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

  @Test
  void toDomain_shouldMapCorrectly() {
    // Given
    CharacterResponse response = new CharacterResponse();
    response.setCharId(CHAR_UUID.toString());
    response.setName(CHARACTER_NAME);
    response.setRace(RACE);
    response.setGender(GENDER);
    response.setAge(AGE);
    response.setWeight(WEIGHT);
    response.setHeight(HEIGHT);
    response.setMoney(MONEY);
    response.setTitle(TITLE);
    response.setHunger(HUNGER);
    response.setThirst(THIRST);
    response.setSleep(SLEEP);

    // When
    Character result = mapper.toDomain(response);

    // Then
    assertNotNull(result);
    assertEquals(CHAR_UUID, result.getId());

    assertEquals(CHARACTER_NAME, result.getName());
    assertEquals(RACE, result.getRace());
    assertEquals(GENDER, result.getGender());
    assertEquals(AGE, result.getAge());
    assertEquals(WEIGHT, result.getWeight());
    assertEquals(HEIGHT, result.getHeight());
    assertEquals(MONEY, result.getMoney());
    assertEquals(TITLE, result.getTitle());
    assertEquals(HUNGER, result.getHunger());
    assertEquals(THIRST, result.getThirst());
    assertEquals(SLEEP, result.getSleep());
  }

  @Test
  void toResponse_shouldMapCorrectly() {
    // Given
    UUID charId = UUID.randomUUID();
    Character character = Character.builder()
        .id(charId)
        .name(CHARACTER_NAME)
        .race(RACE)
        .gender(GENDER)
        .age(AGE)
        .weight(WEIGHT)
        .height(HEIGHT)
        .money(MONEY)
        .title(TITLE)
        .hunger(HUNGER)
        .thirst(THIRST)
        .sleep(SLEEP)
        .build();

    // When
    CharacterResponse result = mapper.toResponse(character);

    // Then
    assertNotNull(result);
    assertEquals(charId.toString(), result.getCharId());
    assertEquals(CHARACTER_NAME, result.getName());
    assertEquals(RACE, result.getRace());
    assertEquals(GENDER, result.getGender());
    assertEquals(AGE, result.getAge());
    assertEquals(WEIGHT, result.getWeight());
    assertEquals(HEIGHT, result.getHeight());
    assertEquals(MONEY, result.getMoney());
    assertEquals(TITLE, result.getTitle());
    assertEquals(HUNGER, result.getHunger());
    assertEquals(THIRST, result.getThirst());
    assertEquals(SLEEP, result.getSleep());
  }
}