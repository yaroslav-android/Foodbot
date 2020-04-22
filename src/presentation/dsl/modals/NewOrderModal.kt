package team.uptech.food.bot.presentation.dsl.modals

import team.uptech.food.bot.presentation.dsl.modals.core.BaseModal
import team.uptech.food.bot.presentation.dsl.modals.builder.Modal

class NewOrderModal : BaseModal() {
  override fun assemble(block: Modal.() -> Unit): String = NewOrderModal().apply(
    Modal().apply(block))
}