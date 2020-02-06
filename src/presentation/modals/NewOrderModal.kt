package team.uptech.food.bot.presentation.modals

import team.uptech.food.bot.presentation.modals.models.Modal

class NewOrderModal : BaseModal() {
  override fun assemble(block: Modal.() -> Unit): String = NewOrderModal().apply(Modal().apply(block))
}