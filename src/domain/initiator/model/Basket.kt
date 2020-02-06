package team.uptech.food.bot.domain.initiator.model

import team.uptech.food.bot.domain.client.model.Client
import team.uptech.food.bot.domain.order.model.Fee

data class Basket(
  val clients: List<Client>,
  val deliveryFee: Fee
)