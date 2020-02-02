package team.uptech.food.bot.domain.order.model

import team.uptech.food.bot.order.client.Client
import team.uptech.food.bot.order.client.Reservation
import team.uptech.food.bot.order.initiation.Basket
import team.uptech.food.bot.order.initiation.Initiator
import team.uptech.food.bot.order.initiation.MenuLink
import team.uptech.food.bot.order.state.OrderStatus

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