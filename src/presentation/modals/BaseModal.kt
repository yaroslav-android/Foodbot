package team.uptech.food.bot.presentation.modals

abstract class BaseModal {
  abstract fun assemble(triggerId: String): String
}