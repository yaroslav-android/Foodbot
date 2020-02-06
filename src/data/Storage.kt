package team.uptech.food.bot.data

import team.uptech.food.bot.domain.initiator.model.Initiator

/**
 * Temporary storage instead of real DB
 */

interface Storage {
  suspend fun updateInitiator(initiator: Initiator)
  suspend fun updateOrder(orderId: Long)
  suspend fun deleteOrder(orderId: Long)
}