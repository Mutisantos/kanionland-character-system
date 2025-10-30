package com.kanionland.charsheet.bff.application.ports;

import com.kanionland.charsheet.bff.application.commands.GameActionCommand;

public interface GameSessionPort {

  void pushGameSessionAction(String sessionId, GameActionCommand command);
}
