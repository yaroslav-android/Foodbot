package team.uptech.food.bot.domain.client.model

data class Client(
  val id: String,
  val name: String,
  val choice: ClientChoice
)