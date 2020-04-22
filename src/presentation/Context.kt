package team.uptech.food.bot.presentation

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.client.HttpClient

data class Context(
  val app: Application,
  val client: HttpClient
) {
  lateinit var call: ApplicationCall
}