package team.uptech.food.bot.order.initiation

import team.uptech.food.bot.order.client.ClientChoice
import team.uptech.food.bot.order.fee.Fee

data class Basket(
  val clientChoices: List<ClientChoice>,
  val deliveryFee: Fee
)