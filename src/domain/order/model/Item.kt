package team.uptech.food.bot.domain.order.model

data class Item(
  val amount: Int,
  val name: String,
  val price: Double?
)