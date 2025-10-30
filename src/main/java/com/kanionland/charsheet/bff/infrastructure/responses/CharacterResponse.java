package com.kanionland.charsheet.bff.infrastructure.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterResponse {

  @JsonProperty("char_id")
  private String charId;
  private String name;
  private String race;
  private String gender;
  private int age;
  private int weight;
  private int height;
  private int money;
  private String title;
  private int hunger;
  private int thirst;
  private int sleep;
  private Map<String, Object> inventory;
  private Map<String, Object> equippedItems;
  private List<String> parts;
}