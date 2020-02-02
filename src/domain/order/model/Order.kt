package team.uptech.food.bot.domain.order.model

import team.uptech.food.bot.domain.initiator.model.Basket
import team.uptech.food.bot.domain.initiator.model.Initiator

data class Order(
  val id: Long,
  val initiator: Initiator,
  val clients: List<Client>,
  val reservation: List<Reservation>,
  val menu: MenuLink,
  val basket: Basket,
  val capacity: Int,
  val status: OrderStatus
)