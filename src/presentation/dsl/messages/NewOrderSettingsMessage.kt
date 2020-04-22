package team.uptech.food.bot.presentation.dsl.messages

import team.uptech.food.bot.presentation.dsl.messages.core.BaseMessage
import team.uptech.food.bot.presentation.dsl.messages.models.Message

class NewOrderSettingsMessage : BaseMessage() {
  override fun assemble(block: Message.() -> Unit): String = NewOrderSettingsMessage().apply(Message().apply(block))
}