package team.uptech.food.bot.presentation.errors

object ErrorBuilder {
  fun assembleNewOrderError() = NewOrderError()
    .assemble {
      errors {
        "" to ""
      }
    }
}