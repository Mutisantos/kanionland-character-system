package com.kanionland.charsheet.bff.infrastructure.clients;



import com.kanionland.charsheet.bff.infrastructure.dtos.CharacterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "characterServiceClient", url = "${external-api.character-service.url}:${external-api.character-service.port}")
public interface CharactersClient {
  @GetMapping("/characters")
  List<CharacterResponse> getCharacters(
      @RequestHeader("Authorization") String authToken,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
      @RequestParam(value = "page_number", defaultValue = "0") int pageNumber
  );
}