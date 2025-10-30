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
public class TravelActionDto implements ActionDto {

  private String destination;
  private int travelTime;

  @Override
  @JsonIgnore
  public String getType() {
    return "TRAVEL";
  }
}
