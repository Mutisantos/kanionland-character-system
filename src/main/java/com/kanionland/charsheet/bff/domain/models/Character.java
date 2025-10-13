package com.kanionland.charsheet.bff.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {

  private UUID id;
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
}