package team.uptech.food.bot.data

import team.uptech.food.bot.domain.initiator.model.Initiator
import team.uptech.food.bot.domain.order.model.Order
import team.uptech.food.bot.utils.Transaction

/**
 * Temporary storage instead of real DB
 */

interface Storage {
  suspend fun updateInitiator(initiator: Initiator): Transaction
  suspend fun insertOrder(order: Order): Transaction
  suspend fun updateOrder(order: Order): Transaction
  suspend fun deleteOrder(orderId: Long): Transaction
}