package com.kanionland.charsheet.bff.infrastructure.controllers;

import com.kanionland.charsheet.bff.application.ports.CharactersPort;
import com.kanionland.charsheet.bff.domain.models.Character;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

  private final CharactersPort characterPort;

  @GetMapping
  public ResponseEntity<List<Character>> getCharacters(
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
      @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber) {

    List<Character> characters = characterPort.getCharacters(offset, pageSize, pageNumber);
    return ResponseEntity.ok(characters);
  }
}