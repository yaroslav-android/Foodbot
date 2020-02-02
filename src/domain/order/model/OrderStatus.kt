package team.uptech.food.bot.domain.order.model

sealed class OrderStatus(data: Any) {
  data class AcceptingOrders(val data: Any) : OrderStatus(data)
  data class Ordering(val data: Any) : OrderStatus(data)
  data class Ordered(val data: Any) : OrderStatus(data)
  data class Delivered(val data: Any) : OrderStatus(data)
  data class Canceled(val data: Any) : OrderStatus(data)
}