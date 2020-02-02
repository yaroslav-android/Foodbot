package team.uptech.food.bot.order.state

import team.uptech.food.bot.order.components.Restaurant
import team.uptech.food.bot.order.initiation.MenuLink
import java.util.*

data class OrderPreview(
  val restaurant: Restaurant,
  val menu: MenuLink,
  val orderStatus: OrderStatus,
  val closeDealTime: Date,
  val capacity: Int,
  val estimatedArrival: Date
)