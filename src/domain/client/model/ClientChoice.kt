package team.uptech.food.bot.order.client

import team.uptech.food.bot.order.components.Item

data class ClientChoice(
  val items: List<Item>,
  val notes: String
)