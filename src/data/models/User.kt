package team.uptech.food.bot.bot.db.models

import team.uptech.food.bot.data.models.CreditCard

data class User(
  val id: String,
  val name: String,
  val card: CreditCard,
  val activeOrders:List<Long>
)