package com.kanionland.charsheet.bff.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.kanionland.charsheet.bff.application.ports.CharactersPort;
import com.kanionland.charsheet.bff.domain.models.Character;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

  @Mock
  private CharactersPort charactersPort;

  @InjectMocks
  private CharacterController controller;

  @Test
  void getCharacters_shouldReturnCharacters() {
    // Given
    UUID charId = UUID.randomUUID();
    Character character = Character.builder().id(charId).build();

    when(charactersPort.getCharacters(anyInt(), anyInt(), anyInt()))
        .thenReturn(List.of(character));

    // When
    ResponseEntity<List<Character>> response = controller.getCharacters(0, 10, 0);

    // Then
    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
    assertEquals(charId, response.getBody().get(0).getId());
  }
}
