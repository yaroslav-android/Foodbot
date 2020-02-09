package team.uptech.food.bot.domain.initiator

import io.ktor.application.Application
import io.ktor.application.log
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.call.receive
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import team.uptech.food.bot.data.Storage
import team.uptech.food.bot.data.models.CreditCard
import team.uptech.food.bot.domain.initiator.model.Initiator
import team.uptech.food.bot.slack.API
import team.uptech.food.bot.utils.extensions.getBotToken
import team.uptech.food.bot.utils.extensions.getToken
import javax.inject.Inject

class InitiatorUseCaseImpl @Inject constructor(
  private val application: Application,
  private val client: HttpClient,
  private val storage: Storage
) : InitiatorUseCase {

  override suspend fun saveInitiator(userId: String?) {
    withContext(Dispatchers.Default) {
      val response = client.call(API.USER_PROFILE) {
        method = HttpMethod.Get
        header(API.HEADER, API.header(getToken()))
        parameter(API.HEADER_TOKEN, getBotToken())
        parameter(API.HEADER_USER, userId)
      }.response

      response.call.receive<String>().also { payload -> parse(payload) }
    }
  }

  private suspend fun parse(payload: String) {
    if (payload.isNotBlank()) {

      // TODO: parse payload
      storage.updateInitiator(
        Initiator(
          id = "test",
          name = "Test Test",
          card = CreditCard("5175 0000 0000 0001"),
          activeOrderIds = listOf()
        )
      )
      application.log.debug(payload)
    }
  }
}