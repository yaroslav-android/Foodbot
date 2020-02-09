package team.uptech.food.bot.utils.logs

import io.ktor.application.Application
import io.ktor.application.log
import javax.inject.Inject

class DebugLogger @Inject constructor(val application: Application) : Logger {

  override fun log(msg: String) {
    application.log.debug(msg)
  }

}