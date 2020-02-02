package team.uptech.food.bot.domain.client.model

import team.uptech.food.bot.domain.order.model.Item

data class ClientChoice(
  val items: List<Item>,
  val notes: String,
  val clientId: String
)