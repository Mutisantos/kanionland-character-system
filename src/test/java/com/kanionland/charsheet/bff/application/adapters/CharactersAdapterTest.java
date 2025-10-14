package com.kanionland.charsheet.bff.application.adapters;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.kanionland.charsheet.bff.application.mappers.CharacterMapper;
import com.kanionland.charsheet.bff.domain.models.Character;
import com.kanionland.charsheet.bff.infrastructure.clients.CharactersClient;
import com.kanionland.charsheet.bff.infrastructure.dtos.CharacterResponse;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CharactersAdapterTest {

  @Mock
  private CharactersClient client;

  @Mock
  private CharacterMapper mapper;

  @InjectMocks
  private CharactersAdapter adapter;

  @Test
  void whenGetCharacters_thenReturnMappedCharactersFromClient() {
    // Given
    final UUID charId = UUID.randomUUID();
    final CharacterResponse clientResponse = new CharacterResponse();
    clientResponse.setCharId(charId.toString());

    final Character expectedCharacter = Character.builder().id(charId).build();

    when(client.getCharacters(anyInt(), anyInt(), anyInt())).thenReturn(List.of(clientResponse));
    when(mapper.toDomain(clientResponse)).thenReturn(expectedCharacter);

    // When
    List<Character> result = adapter.getCharacters(0, 10, 0);

    // Then
    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(charId, result.get(0).getId());
  }
}
