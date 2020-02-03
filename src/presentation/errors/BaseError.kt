package team.uptech.food.bot.presentation.errors

import com.google.gson.Gson

abstract class BaseError {
  abstract fun assemble(block: Error.() -> Unit): String

  fun apply(error: Error): String = Gson().toJson(error)
}