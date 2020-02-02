package team.uptech.food.bot.data

import team.uptech.food.bot.domain.initiator.model.Initiator

class DataStorage : Storage {
  override suspend fun updateInitiator(initiator: Initiator) {
    // TODO: implement logic
  }

  override suspend fun updateOrder(orderId: Long) {
    // TODO: implement logic
  }

  override suspend fun deleteOrder(orderId: Long) {
    // TODO: implement logic
  }
}