package team.uptech.food.bot.presentation.models

sealed class ButtonStyle(val style: String?) {
  object Primary : ButtonStyle("primary")
  object Danger : ButtonStyle("danger")
  object Default : ButtonStyle(null)
}