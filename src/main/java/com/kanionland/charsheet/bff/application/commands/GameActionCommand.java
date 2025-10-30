package com.kanionland.charsheet.bff.application.commands;

import com.kanionland.charsheet.bff.application.commands.states.actions.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameActionCommand {

  private String username;
  private String description;
  private String character;
  private Action action;

  public ActionType getType() {
    return action != null ? action.getType() : null;
  }
}
