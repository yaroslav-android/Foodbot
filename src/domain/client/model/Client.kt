package team.uptech.food.bot.domain.client.model

data class Client(
  val id: String,
  val name: String,
  val choice: ClientChoice
) {
  val total: Double
    get() = choice.items.sumByDouble { it.price ?: 0.0 }
}