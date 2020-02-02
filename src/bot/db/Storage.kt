package team.uptech.food.bot.bot.db

import team.uptech.food.bot.initiation.Initiator

/**
 * Temporary storage instead of real DB
 */

interface Storage {
  suspend fun updateInitiator(initiator: Initiator)
  suspend fun updateOrder(orderId: Long)
  suspend fun deleteOrder(orderId: Long)
}