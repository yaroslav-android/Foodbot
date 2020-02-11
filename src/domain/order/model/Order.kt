package team.uptech.food.bot.domain.order.model

import team.uptech.food.bot.domain.client.model.Client
import team.uptech.food.bot.domain.initiator.model.Initiator
import team.uptech.food.bot.slack.interactions.Channel

data class Order(
  val id: Long,
  val channel: Channel,
  val initiator: Initiator,
  val clients: List<Client>,
  val reservation: List<Reservation>,
  val menu: MenuLink,
  val capacity: Int,
  val status: OrderStatus
)