package team.uptech.food.bot.initiation

import team.uptech.food.bot.order.state.OrderStatus

sealed class InitiatorAction {
  data class UpdateStatus(val orderStatus: OrderStatus) : InitiatorAction()
  data class UpdateETA(val date: Any) : InitiatorAction()
}