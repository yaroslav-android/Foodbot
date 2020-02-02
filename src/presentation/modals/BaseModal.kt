package team.uptech.food.bot.modals

abstract class BaseModal {
  abstract fun assemble(triggerId: String): String
}