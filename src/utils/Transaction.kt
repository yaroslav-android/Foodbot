package team.uptech.food.bot.utils

sealed class Transaction(val message: String) {
  class Failure(message: String) : Transaction(message)
  object Success : Transaction("")
}