package com.kanionland.charsheet.bff.infrastructure.requests;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.ActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.CombatActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.DiceRollActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.InteractionActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.PurchaseActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.TravelActionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameActionRequest {

  @NotBlank
  private String username;
  @NotBlank
  private String character;

  private String description;

  @Valid
  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXISTING_PROPERTY,
      property = "type",
      visible = true
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(value = CombatActionDto.class, name = "COMBAT"),
      @JsonSubTypes.Type(value = PurchaseActionDto.class, name = "PURCHASE"),
      @JsonSubTypes.Type(value = DiceRollActionDto.class, name = "DICE_ROLL"),
      @JsonSubTypes.Type(value = InteractionActionDto.class, name = "INTERACTION"),
      @JsonSubTypes.Type(value = TravelActionDto.class, name = "TRAVEL")
  })
  private ActionDto action;
}

