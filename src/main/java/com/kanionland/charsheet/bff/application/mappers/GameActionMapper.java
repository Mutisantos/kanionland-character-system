package com.kanionland.charsheet.bff.application.mappers;

import com.kanionland.charsheet.bff.application.commands.CombatActionType;
import com.kanionland.charsheet.bff.application.commands.GameActionCommand;
import com.kanionland.charsheet.bff.application.commands.states.actions.Action;
import com.kanionland.charsheet.bff.application.commands.states.actions.CombatAction;
import com.kanionland.charsheet.bff.application.commands.states.actions.DiceRollAction;
import com.kanionland.charsheet.bff.application.commands.states.actions.InteractionAction;
import com.kanionland.charsheet.bff.application.commands.states.actions.PurchaseAction;
import com.kanionland.charsheet.bff.application.commands.states.actions.TravelAction;
import com.kanionland.charsheet.bff.domain.models.Item;
import com.kanionland.charsheet.bff.infrastructure.requests.GameActionRequest;
import com.kanionland.charsheet.bff.infrastructure.requests.ItemDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.ActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.CombatActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.DiceRollActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.InteractionActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.PurchaseActionDto;
import com.kanionland.charsheet.bff.infrastructure.requests.states.actions.TravelActionDto;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper(componentModel = "spring")
public interface GameActionMapper {

  GameActionCommand toCommand(GameActionRequest request, UUID sessionId);

  @Mapping(target = "actionType", source = "actionType")
  CombatAction toCombatAction(CombatActionDto dto);

  @ValueMappings({
      @ValueMapping(target = "ACTION", source = "ACTION"),
      @ValueMapping(target = "COUNTER", source = "COUNTER"),
      @ValueMapping(target = "REACTION", source = "REACTION")
  })
  CombatActionType toCombatActionType(String actionType);

  PurchaseAction toPurchaseAction(PurchaseActionDto dto);

  @Mapping(source = "statValue", target = "statValue")
  DiceRollAction toDiceRollAction(DiceRollActionDto dto);

  InteractionAction toInteractionAction(InteractionActionDto dto);

  TravelAction toTravelAction(TravelActionDto dto);

  Item toItem(ItemDto dto);

  default Action toAction(ActionDto dto) {
    if (dto == null) {
      return null;
    }
    if (dto instanceof CombatActionDto) {
      return toCombatAction((CombatActionDto) dto);
    } else if (dto instanceof PurchaseActionDto) {
      return toPurchaseAction((PurchaseActionDto) dto);
    } else if (dto instanceof DiceRollActionDto) {
      return toDiceRollAction((DiceRollActionDto) dto);
    } else if (dto instanceof InteractionActionDto) {
      return toInteractionAction((InteractionActionDto) dto);
    } else if (dto instanceof TravelActionDto) {
      return toTravelAction((TravelActionDto) dto);
    }
    throw new IllegalArgumentException("Unknown action type: " + dto.getClass().getName());
  }
}