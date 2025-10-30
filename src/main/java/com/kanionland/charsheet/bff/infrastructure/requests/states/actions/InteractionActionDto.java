package com.kanionland.charsheet.bff.infrastructure.requests.states.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InteractionActionDto implements ActionDto {

  private String interactionType;
  private String interactionTarget;
  private String associatedStat;
  private int value;

  @Override
  @JsonIgnore
  public String getType() {
    return "INTERACTION";
  }
}
