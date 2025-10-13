package com.kanionland.charsheet.bff.application.adapters;

import com.kanionland.charsheet.bff.application.ports.CharactersPort;
import com.kanionland.charsheet.bff.domain.models.Character;
import com.kanionland.charsheet.bff.infrastructure.clients.CharactersClient;
import com.kanionland.charsheet.bff.infrastructure.dtos.CharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CharactersAdapter implements CharactersPort {
  private final CharactersClient characterClient;

  @Override
  public List<Character> getCharacters(int offset, int pageSize, int pageNumber) {
    List<CharacterResponse> responses = characterClient.getCharacters(offset, pageSize, pageNumber);
    return responses.stream()
        .map(this::mapToCharacter)
        .collect(Collectors.toList());
  }

  private Character mapToCharacter(CharacterResponse response) {
    return Character.builder()
        .id(UUID.fromString(response.getCharId()))
        .name(response.getName())
        .race(response.getRace())
        .gender(response.getGender())
        .age(response.getAge())
        .weight(response.getWeight())
        .height(response.getHeight())
        .money(response.getMoney())
        .title(response.getTitle())
        .hunger(response.getHunger())
        .thirst(response.getThirst())
        .sleep(response.getSleep())
        .build();
  }
}