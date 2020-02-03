package team.uptech.food.bot.presentation.messages

import team.uptech.food.bot.presentation.messages.models.Message

class NewOrderSettingsMessage : BaseMessage() {
  override fun assemble(block: Message.() -> Unit): String = NewOrderSettingsMessage().apply(Message().apply(block))
}