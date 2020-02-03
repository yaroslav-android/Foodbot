package team.uptech.food.bot

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.call.receive
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.content.TextContent
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.slf4j.event.Level
import team.uptech.food.bot.bot.Bot
import team.uptech.food.bot.bot.BotReplay
import team.uptech.food.bot.data.DataStorage
import team.uptech.food.bot.data.Storage
import team.uptech.food.bot.presentation.messages.MessageBuilder
import team.uptech.food.bot.presentation.modals.ModalBuilder
import team.uptech.food.bot.slack.API
import team.uptech.food.bot.utils.getBotToken
import team.uptech.food.bot.utils.getToken

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

// Referenced in application.conf
@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

  install(DefaultHeaders)

  install(ContentNegotiation) {
    gson { setPrettyPrinting() }
  }

  install(CallLogging) {
    level = Level.DEBUG
  }

  val client = HttpClient(Apache)
  val botReplay = BotReplay()
  // TODO: put all needed data from this object into Initiator
  var userProfile = JsonObject()
  val storage: Storage = DataStorage()

  routing {
    get(Bot.HOME_PATH) { call.respondText(botReplay.hello(), contentType = ContentType.Text.Plain) }

    post(Bot.NEW_ORDER_PATH) {
      // TODO: save CHANNEL_ID, USER_ID as initiator
      // TODO: check if a user has already started order ( decline drops after successful or canceled order )
      // TODO: make sure that we locked on channel from where user requested the order
      val parameters = call.receiveParameters()
      log.debug(parameters.entries().toString())
      val triggerId = parameters[API.TRIGGER_ID]
      val userId = parameters[API.USER_ID]
      val channelId = parameters[API.CHANNEL_ID]

      if (!triggerId.isNullOrBlank()) {
        // TODO: extract into API extension
        // TODO: pass type of modal and assemble it
        val response = client.call(API.VIEW_OPEN) {
          method = HttpMethod.Post
          header(API.HEADER, API.header(getToken()))
          body = TextContent(ModalBuilder.assembleNewOrder(triggerId), contentType = ContentType.Application.Json)
        }.response

        // get "view":{"id":"VS5L99ZD1" to update modal, keep it
        log.debug(response.call.receive())
      }

      // TODO: this profile info of initiator should be saved in future
      val response = client.call(API.USER_PROFILE) {
        method = HttpMethod.Get
        header(API.HEADER, API.header(getToken()))
        parameter("token", getBotToken())
        parameter("user", userId)
      }.response

      response.call.receive<String>().also {
        if (it.isNotBlank()) {
          userProfile = JsonParser().parse(it) as JsonObject
          log.debug(it)
        }
      }

//            call.respondText(getMessage(), ContentType.Application.Json)
      call.respond(HttpStatusCode.OK, "")

      /* keep for future usage ->>
      val response = client.call(API.SEND_MSG) {
          method = HttpMethod.Post
          header(API.HEADER, API.header(getToken()))
          body = TextContent(getMessage(), contentType = ContentType.Application.Json)
      }.response

      log.debug(response.call.receive())
      */
    }

    post(Bot.MANUAL_RESET_PATH) {
      val parameters = call.receiveParameters()
      log.debug(parameters.entries().toString())
//            val userId = parameters[API.USER_ID]
      // TODO: drop all data related to user and his initiated an order.
      //  consider add confirmation dialog if user just wanted to check how it behaves in the middle of the order
      call.respondText("${userProfile["profile"].asJsonObject["real_name"].asString}, you now able to initiate a new order :stew:")
    }

    post(Bot.USER_INTERACTIONS) {
      val parameters = call.receiveParameters()
      log.debug(parameters.entries().toString())

      val view = Gson().fromJson(parameters["payload"], CallbackId::class.java)

      if (view?.view != null && "order_initiation" == view.view.callbackId) {
        val response = client.call(API.SEND_MSG_EPH) {
          method = HttpMethod.Post
          header(API.HEADER, API.header(getToken()))
          body = TextContent(
            MessageBuilder.assembleOrderSettings(getBotToken(), view.user.id),
            contentType = ContentType.Application.Json
          )
        }.response
        log.debug(response.call.receive())

        call.respond(HttpStatusCode.OK, "")
      } else {
        call.respond(HttpStatusCode.OK, "")
      }

      /* GET user input from modal BY ->
      "view": {
          "state": {
              "values": {}
           }
       }
      */

      // TODO: add fields validation for presentation.modals
//      call.respondText(ErrorBuilder.assembleNewOrderError(), ContentType.Application.Json, HttpStatusCode.OK)
    }
  }
}

/* TODO: extract from here to data layer */
data class CallbackId(@SerializedName("view") val view: View, @SerializedName("user") val user: User)

data class View(@SerializedName("callback_id") val callbackId: String)
data class User(@SerializedName("id") val id: String)
