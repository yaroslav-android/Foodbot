package team.uptech.food.bot.data

import domain.order.model.initiation.Initiator

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