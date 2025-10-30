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
public class CombatActionDto implements ActionDto {

  private String actionType;
  private String stat;
  private String target;
  private int value;

  @Override
  @JsonIgnore
  public String getType() {
    return "COMBAT";
  }
}
