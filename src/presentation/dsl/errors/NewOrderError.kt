package team.uptech.food.bot.presentation.dsl.errors

import team.uptech.food.bot.presentation.dsl.errors.core.BaseError
import team.uptech.food.bot.presentation.dsl.errors.models.Error

class NewOrderError : BaseError() {
  override fun assemble(block: Error.() -> Unit): String = NewOrderError().apply(
    Error().apply(block))
}