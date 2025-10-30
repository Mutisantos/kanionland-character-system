package com.kanionland.charsheet.bff.application.commands.states.actions;

import com.kanionland.charsheet.bff.application.commands.ActionType;
import com.kanionland.charsheet.bff.domain.models.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PurchaseAction implements Action {

  private final int amount;
  private final String saleLocation;
  private final Item item;

  @Override
  public ActionType getType() {
    return ActionType.PURCHASE;
  }
}
