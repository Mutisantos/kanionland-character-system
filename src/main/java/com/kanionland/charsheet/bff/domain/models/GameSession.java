package com.kanionland.charsheet.bff.domain.models;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GameSession {

  private UUID sessionId;
  private String username;
  private String description;
  private String character;
  private Map<String, Object> action;
  
}

