package team.uptech.food.bot.domain.client.model

sealed class ClientAction {
  object Add : ClientAction()
  object Remove : ClientAction()
  object Reserve : ClientAction()
}