package com.kanionland.charsheet.bff.application.mappers;

import com.kanionland.charsheet.bff.domain.models.Character;
import com.kanionland.charsheet.bff.infrastructure.dtos.CharacterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

  CharacterMapper INSTANCE = Mappers.getMapper(CharacterMapper.class);

  @Mapping(target = "charId", source = "id")
  CharacterResponse toResponse(Character character);

  @Mapping(target = "id", source = "charId")
  Character toDomain(CharacterResponse response);
}