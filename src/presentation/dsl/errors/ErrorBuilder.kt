package team.uptech.food.bot.presentation.dsl.errors

object ErrorBuilder {
  fun assembleNewOrderError() = NewOrderError()
    .assemble {
      errors {
        "" to ""
      }
    }
}