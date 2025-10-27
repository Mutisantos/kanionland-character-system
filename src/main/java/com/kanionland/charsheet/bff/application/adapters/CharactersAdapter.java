package com.kanionland.charsheet.bff.application.adapters;

import com.kanionland.charsheet.bff.application.mappers.CharacterMapper;
import com.kanionland.charsheet.bff.application.ports.CharactersPort;
import com.kanionland.charsheet.bff.domain.models.Character;
import com.kanionland.charsheet.bff.infrastructure.clients.CharactersClient;
import com.kanionland.charsheet.bff.infrastructure.dtos.CharacterResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersAdapter implements CharactersPort {
  private final CharactersClient characterClient;
  private final CharacterMapper characterMapper;

  @Override
  public List<Character> getCharacters(String authToken, int offset, int pageSize, int pageNumber) {
    List<CharacterResponse> responses = characterClient.getCharacters(authToken, offset, pageSize, pageNumber);
    return responses.stream()
        .map(characterMapper::toDomain)
        .collect(Collectors.toList());
  }
}