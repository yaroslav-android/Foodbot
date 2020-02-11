package team.uptech.food.bot.presentation.new_order

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.log
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.call
import io.ktor.client.call.receive
import io.ktor.client.request.header
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import team.uptech.food.bot.domain.initiator.InitiatorUseCase
import team.uptech.food.bot.presentation.messages.MessageBuilder
import team.uptech.food.bot.presentation.modals.ModalBuilder
import team.uptech.food.bot.slack.API
import team.uptech.food.bot.utils.extensions.getBotToken
import team.uptech.food.bot.utils.extensions.getToken
import javax.inject.Inject


class NewOrderPresenterImpl @Inject constructor(
  private val application: Application,
  private val client: HttpClient,
  private val initiatorUseCase: InitiatorUseCase
) : NewOrderPresenter {

  override suspend fun startNewOrder(pipelineContext: PipelineContext<Unit, ApplicationCall>) {

    // TODO: check if a user has already started order ( decline drops after successful or canceled order )
    // TODO: make sure that we locked on channel from where user requested the order

    val parameters = pipelineContext.call.receiveParameters()
    application.log.debug(parameters.entries().toString())

    val triggerId = parameters[API.TRIGGER_ID]
    val userId = parameters[API.USER_ID]

    openNewOrderModal(triggerId)
    initiatorUseCase.saveInitiator(userId)

    pipelineContext.call.respond(HttpStatusCode.OK, "")
  }

  private suspend fun openNewOrderModal(triggerId: String?) {
    withContext(Dispatchers.Default) {
      if (!triggerId.isNullOrBlank()) {
        val modal = ModalBuilder.assembleNewOrder(triggerId)

        with(newOrder(modal)) { application.log.debug(response.call.receive()) }
      }
    }
  }

  private suspend fun newOrder(modal: String): HttpClientCall {
    return client.call(API.VIEW_OPEN) {
      method = HttpMethod.Post
      header(API.HEADER, API.header(getToken()))
      body = TextContent(modal, contentType = ContentType.Application.Json)
    }
  }

  override suspend fun processNewOrder(
    pipelineContext: PipelineContext<Unit, ApplicationCall>,
    payload: String?
  ) {
    val view = Gson().fromJson(payload, Payload::class.java)
    with(newControlMessage(view.user.id, "")) { application.log.debug(response.call.receive()) }

    pipelineContext.call.respond(HttpStatusCode.OK, "")
  }

  private suspend fun newControlMessage(userId: String, channelId: String): HttpClientCall {
    return client.call(API.SEND_MSG_EPH) {
      method = HttpMethod.Post
      header(API.HEADER, API.header(getToken()))
      body = TextContent(
        MessageBuilder.assembleOrderSettings(getBotToken(), userId /*, channelId*/),
        contentType = ContentType.Application.Json
      )
    }
  }

  // FIXME: Get rid of this classes in future
  data class Payload(@SerializedName("view") val view: View, @SerializedName("user") val user: User)

  data class View(@SerializedName("callback_id") val callbackId: String)
  data class User(@SerializedName("id") val id: String)
}