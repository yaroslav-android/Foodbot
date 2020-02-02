package team.uptech.food.bot.domain.order.model

import java.util.*

data class OrderPreview(
  val restaurant: Restaurant,
  val menu: MenuLink,
  val orderStatus: OrderStatus,
  val closeDealTime: Date,
  val capacity: Int,
  val estimatedArrival: Date
)