package team.uptech.food.bot.presentation.modals

import com.google.gson.Gson
import team.uptech.food.bot.presentation.modals.models.Modal

abstract class BaseModal {
  abstract fun assemble(block: Modal.() -> Unit): String

  fun apply(modal: Modal): String = Gson().toJson(modal)
}