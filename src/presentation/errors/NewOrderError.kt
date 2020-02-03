package team.uptech.food.bot.presentation.errors

class NewOrderError : BaseError() {
  override fun assemble(block: Error.() -> Unit): String = NewOrderError().apply(Error().apply(block))
}