package team.uptech.food.bot.presentation.messages

import com.google.gson.Gson
import team.uptech.food.bot.presentation.messages.models.Message

abstract class BaseMessage {
  abstract fun assemble(block: Message.() -> Unit): String

  fun apply(message: Message): String = Gson().toJson(message)
}