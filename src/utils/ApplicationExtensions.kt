package team.uptech.food.bot.utils

import io.ktor.application.Application
import team.uptech.food.bot.bot.Bot


fun Application.getToken() = System.getenv()[Bot.TOKEN] ?: ""
fun Application.getBotToken() = System.getenv()[Bot.BOT_TOKEN] ?: ""
