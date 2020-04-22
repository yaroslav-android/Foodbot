package team.uptech.food.bot.presentation.dsl.modals.core

import com.google.gson.Gson
import team.uptech.food.bot.presentation.dsl.modals.builder.Modal

abstract class BaseModal {
  abstract fun assemble(block: Modal.() -> Unit): String

  fun apply(modal: Modal): String = Gson().toJson(modal)
}