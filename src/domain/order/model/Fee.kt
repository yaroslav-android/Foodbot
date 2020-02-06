package team.uptech.food.bot.domain.order.model

sealed class Fee(val amount: Double) {
  data class Pay(val fee: Double) : Fee(fee)
  object Free : Fee(0.0)
}