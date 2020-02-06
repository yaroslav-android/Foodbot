package team.uptech.food.bot.domain.initiator.model

import team.uptech.food.bot.data.models.CreditCard

data class Initiator(
  val id: String,
  val name: String,
  val card: CreditCard,
  val activeOrderIds: List<Long>
)