package team.uptech.food.bot

import com.google.gson.Gson
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.log
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import team.uptech.food.bot.bot.Bot
import team.uptech.food.bot.bot.BotReplay
import team.uptech.food.bot.data.Storage
import team.uptech.food.bot.data.respones.Payload
import team.uptech.food.bot.di.Injector.injectApplication
import team.uptech.food.bot.presentation.Context
import team.uptech.food.bot.presentation.new_order.makeNewOrder
import team.uptech.food.bot.presentation.new_order.processNewOrder
import team.uptech.food.bot.utils.extensions.configure
import javax.inject.Inject


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


class EntryPoint(application: Application) {

  @Inject
  lateinit var storage: Storage

  @Inject
  lateinit var botReplay: BotReplay

  init {
    injectApplication(application)
  }
}

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
  configure()

  val client = HttpClient(Apache)
  val entry = EntryPoint(this)
  val context = Context(this, client)

  routing {

    get(Bot.HOME_PATH) {
      call.respondText(entry.botReplay.hello(), contentType = ContentType.Text.Plain)
    }

    post(Bot.NEW_ORDER_PATH) {
      context.call = call

      withContext(Dispatchers.Default) {
        makeNewOrder(context)
      }
    }

    post(Bot.MANUAL_RESET_PATH) {
      call.respond(HttpStatusCode.OK, "")
      return@post

      // FIXME: WON'T WORK till this part would be moved into use case with created Order object
      withContext(Dispatchers.Default) {
        val parameters = call.receiveParameters()
        log.debug(parameters.entries().toString())
        // TODO: drop all data related to user and his initiated an order.
        //  consider add confirmation dialog if user just wanted to check how it behaves in the middle of the order
//        call.respondText("${userProfile["profile"].asJsonObject["real_name"].asString}, you now able to initiate a new order :stew:")
      }
    }

    post(Bot.USER_INTERACTIONS) {
      context.call = call

      withContext(Dispatchers.Default) {
        val parameters = call.receiveParameters()
        log.debug(parameters.entries().toString())

        val payload = Gson().fromJson(parameters[Payload.KEY], Payload::class.java)

        // FIXME: hardcoded parameters 👎🏻
        // FIXME: Use block_id and action_id instead of callback_id
        if (payload?.view != null && "order_initiation" == payload.view.callbackId) {
          processNewOrder(context, payload)
        } else {
          call.respond(HttpStatusCode.OK, "")
        }
      }

      // TODO: add fields validation for presentation.dsl.modals
    }
  }
}