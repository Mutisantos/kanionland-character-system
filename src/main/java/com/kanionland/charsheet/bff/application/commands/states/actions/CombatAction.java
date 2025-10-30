package com.kanionland.charsheet.bff.application.commands.states.actions;

import com.kanionland.charsheet.bff.application.commands.ActionType;
import com.kanionland.charsheet.bff.application.commands.CombatActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CombatAction implements Action {

  private final CombatActionType actionType;
  private final String stat;
  private final String target;
  private final int value;

  @Override
  public ActionType getType() {
    return ActionType.COMBAT;
  }
}
