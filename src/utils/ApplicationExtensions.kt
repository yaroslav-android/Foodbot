package team.uptech.food.bot.utils

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import org.slf4j.event.Level
import team.uptech.food.bot.bot.Bot


fun Application.getToken() = System.getenv()[Bot.TOKEN] ?: ""
fun Application.getBotToken() = System.getenv()[Bot.BOT_TOKEN] ?: ""

fun Application.configure() {
  install(DefaultHeaders)

  install(ContentNegotiation) {
    gson { setPrettyPrinting() }
  }

  install(CallLogging) {
    level = Level.DEBUG
  }
}