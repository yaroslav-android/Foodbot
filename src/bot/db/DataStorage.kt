package team.uptech.food.bot.bot.db

import team.uptech.food.bot.initiation.Initiator

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