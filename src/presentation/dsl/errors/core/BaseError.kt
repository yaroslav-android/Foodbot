package team.uptech.food.bot.presentation.dsl.errors.core

import com.google.gson.Gson
import team.uptech.food.bot.presentation.dsl.errors.models.Error

abstract class BaseError {
  abstract fun assemble(block: Error.() -> Unit): String

  fun apply(error: Error): String = Gson().toJson(error)
}