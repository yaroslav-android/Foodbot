package team.uptech.food.bot.domain.initiator

interface InitiatorUseCase {
  suspend fun saveInitiator(userId: String?)
}