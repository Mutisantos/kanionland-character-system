package com.kanionland.charsheet.bff.application.ports;

import com.kanionland.charsheet.bff.domain.models.Character;

import java.util.List;

public interface CharactersPort {

  List<Character> getCharacters(int offset, int pageSize, int pageNumber);
}
