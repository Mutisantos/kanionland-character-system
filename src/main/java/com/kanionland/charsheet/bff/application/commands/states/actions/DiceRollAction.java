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

  private String stat;
  private List<Integer> fateRollValues;
  private int challengeValue;
  private int rollValue;
  private int statValue;

  @Override
  public ActionType getType() {
    return ActionType.DICE_ROLL;
  }
}
