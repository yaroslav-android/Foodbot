package team.uptech.food.bot.domain.initiator.model

import team.uptech.food.bot.domain.order.model.OrderStatus

sealed class InitiatorAction {
  data class UpdateStatus(val orderStatus: OrderStatus) : InitiatorAction()
  data class UpdateETA(val date: Any) : InitiatorAction()
}