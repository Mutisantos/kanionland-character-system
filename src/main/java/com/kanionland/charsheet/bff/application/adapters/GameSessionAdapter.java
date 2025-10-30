package com.kanionland.charsheet.bff.application.adapters;

import com.kanionland.charsheet.bff.application.commands.GameActionCommand;
import com.kanionland.charsheet.bff.application.ports.GameSessionPort;
import com.kanionland.charsheet.bff.infrastructure.clients.GameSessionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameSessionAdapter implements GameSessionPort {

  private final GameSessionClient gameSessionClient;

  @Override
  public void pushGameSessionAction(final String sessionId, final GameActionCommand command) {
    gameSessionClient.sendActionCommand(sessionId, command);
  }
}