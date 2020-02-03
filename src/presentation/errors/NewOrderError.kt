package team.uptech.food.bot.presentation.errors

import team.uptech.food.bot.presentation.errors.models.Error

class NewOrderError : BaseError() {
  override fun assemble(block: Error.() -> Unit): String = NewOrderError().apply(
    Error().apply(block))
}