package team.uptech.food.bot.presentation.new_order

import io.ktor.application.ApplicationCall
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
import team.uptech.food.bot.data.respones.Payload
import team.uptech.food.bot.presentation.Context
import team.uptech.food.bot.presentation.dsl.messages.MessageBuilder
import team.uptech.food.bot.presentation.dsl.modals.ModalBuilder
import team.uptech.food.bot.slack.API
import team.uptech.food.bot.utils.extensions.getBotToken
import team.uptech.food.bot.utils.extensions.getToken


suspend fun <TSubject : Any, TContext : ApplicationCall> PipelineContext<TSubject, TContext>.makeNewOrder(context: Context) {
  // TODO: check if a user has already started order ( decline drops after successful or canceled order )
  // TODO: make sure that we locked on channel from where user requested the order

  val parameters = context.call.receiveParameters()
  context.app.log.debug(parameters.entries().toString())

  val triggerId = parameters[API.TRIGGER_ID]
  val userId = parameters[API.USER_ID]

  openNewOrderModal(context, triggerId)
// TODO  initiatorUseCase.saveInitiator(userId)

  context.call.respond(HttpStatusCode.OK, "")
}

private suspend fun openNewOrderModal(context: Context, triggerId: String?) {
  if (!triggerId.isNullOrBlank()) {
    val modal = ModalBuilder.assembleNewOrder(triggerId)
    with(newOrder(context.client, modal)) { context.app.log.debug(response.call.receive()) }
  }
}

private suspend fun newOrder(client: HttpClient, modal: String): HttpClientCall {
  return client.call(API.VIEW_OPEN) {
    method = HttpMethod.Post
    header(API.HEADER, API.header(getToken()))
    body = TextContent(modal, contentType = ContentType.Application.Json)
  }
}

suspend fun <TSubject : Any, TContext : ApplicationCall> PipelineContext<TSubject, TContext>.processNewOrder(
  context: Context,
  payload: Payload
) {
  with(newControlMessage(context.client, payload.user.id, "")) { context.app.log.debug(response.call.receive()) }
  context.call.respond(HttpStatusCode.OK, "")
}

private suspend fun newControlMessage(client: HttpClient, userId: String, channelId: String): HttpClientCall {
  return client.call(API.SEND_MSG_EPH) {
    method = HttpMethod.Post
    header(API.HEADER, API.header(getToken()))
    body = TextContent(
      MessageBuilder.assembleOrderSettings(getBotToken(), userId /*, channelId*/),
      contentType = ContentType.Application.Json
    )
  }
}
