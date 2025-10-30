package com.kanionland.charsheet.bff.infrastructure.controllers;

import com.kanionland.charsheet.bff.application.mappers.GameActionMapper;
import com.kanionland.charsheet.bff.application.ports.GameSessionPort;
import com.kanionland.charsheet.bff.infrastructure.requests.GameActionRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-session")
@RequiredArgsConstructor
public class GameSessionController {

  private final GameSessionPort gameSessionPort;
  private final GameActionMapper gameActionMapper;

  @PostMapping("/{sessionId}")
  public ResponseEntity<Void> postGameAction(@PathVariable String sessionId,
      @Valid @RequestBody GameActionRequest request) {
    final UUID sessionUuid = UUID.fromString(sessionId);
    gameSessionPort.pushGameSessionAction(sessionId,
        gameActionMapper.toCommand(request, sessionUuid));
    return ResponseEntity.ok().build();
  }
}