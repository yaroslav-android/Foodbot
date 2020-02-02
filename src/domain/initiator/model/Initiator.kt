package team.uptech.food.bot.order.initiation

import team.uptech.food.bot.data.models.CreditCard

data class Initiator(
  val id: String,
  val name: String,
  val card: CreditCard,
  val activeOrderIds: List<Long>
)