package com.kanionland.charsheet.bff.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.kanionland.charsheet.bff.application.commands.GameActionCommand;
import com.kanionland.charsheet.bff.application.mappers.GameActionMapper;
import com.kanionland.charsheet.bff.application.ports.GameSessionPort;
import com.kanionland.charsheet.bff.infrastructure.requests.GameActionRequest;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GameSessionControllerTest {


  @Mock
  private GameSessionPort gameSessionPort;
  @Mock
  private GameActionMapper gameActionMapper;

  @InjectMocks
  private GameSessionController controller;

  @Test
  void getCharacters_shouldReturnCharacters() {
    // Given
    UUID charId = UUID.randomUUID();
    final GameActionRequest request = GameActionRequest.builder().build();
    doNothing().when(gameSessionPort).pushGameSessionAction(anyString(), any());
    when(gameActionMapper.toCommand(eq(request), any())).thenReturn(
        GameActionCommand.builder().build());
    // When
    final ResponseEntity<Void> response = controller.postGameAction(
        "85e583dd-d772-4c78-96b1-d1e60af08952", request);

    // Then
    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
  }
}
