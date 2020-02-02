package team.uptech.food.bot.domain.initiator.model

import team.uptech.food.bot.domain.client.model.ClientChoice
import team.uptech.food.bot.domain.order.model.Fee

data class Basket(
  val clientChoices: List<ClientChoice>,
  val deliveryFee: Fee
)