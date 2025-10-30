package com.kanionland.charsheet.bff.application.commands.states.actions;

import com.kanionland.charsheet.bff.application.commands.ActionType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiceRollAction implements Action {

  private final String stat;
  private final int challengeValue;
  private final int rollValue;
  private final List<Integer> fateRollValues;

  @Override
  public ActionType getType() {
    return ActionType.DICE_ROLL;
  }
}
