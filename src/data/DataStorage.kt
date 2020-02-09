package team.uptech.food.bot.data

import team.uptech.food.bot.domain.initiator.model.Initiator
import team.uptech.food.bot.domain.order.model.Order
import team.uptech.food.bot.utils.Transaction

class DataStorage : Storage {
  private val orders: MutableList<Order> = mutableListOf()

  override suspend fun updateInitiator(initiator: Initiator): Transaction {
    TODO("not implemented logic")
  }

  override suspend fun insertOrder(order: Order): Transaction {
    val index = orders.indexOfFirst { it.id == order.id }

    return Transaction.Success.also {
      if (index == -1) orders.add(order) else updateOrder(order)
    }
  }

  override suspend fun updateOrder(order: Order): Transaction {
    val index = orders.indexOfFirst { it.id == order.id }

    return if (index != -1) {
      Transaction.Success.also { orders[index] = order }
    } else {
      Transaction.Failure("Can't update the order = $order.")
    }
  }

  override suspend fun deleteOrder(orderId: Long): Transaction {
    val index = orders.indexOfFirst { it.id == orderId }

    return if (index != -1) {
      Transaction.Success.also { orders.removeAt(index) }
    } else {
      Transaction.Failure("Can't delete the order with id = $orderId.")
    }
  }
}