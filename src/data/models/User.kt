package team.uptech.food.bot.data.models

data class User(
  val id: String,
  val name: String,
  val card: CreditCard,
  val activeOrders: List<Long>
)