package com.kanionland.charsheet.bff.application.commands.states.actions;

import com.kanionland.charsheet.bff.application.commands.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InteractionAction implements Action {

  private final String interactionType;
  private final String interactionTarget;
  private final String associatedStat;
  private final int value;

  @Override
  public ActionType getType() {
    return ActionType.INTERACTION;
  }
}
