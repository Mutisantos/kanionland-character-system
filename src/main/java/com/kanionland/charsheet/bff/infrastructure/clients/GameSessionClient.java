package com.kanionland.charsheet.bff.infrastructure.clients;


import com.kanionland.charsheet.bff.application.commands.GameActionCommand;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "gameSessionClient", url = "${external-api.game-session-service.url}:${external-api.game-session-service.port}")
public interface GameSessionClient {

  @PostMapping("/game-sessions/{sessionId}")
  void sendActionCommand(@PathVariable String sessionId,
      @Valid @RequestBody GameActionCommand request);
}