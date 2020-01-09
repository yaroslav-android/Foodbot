package team.uptech.food.bot

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.call.receive
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.header
import io.ktor.content.TextContent
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.path
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.slf4j.event.Level
import team.uptech.food.bot.bot.Bot
import team.uptech.food.bot.bot.BotReplay
import team.uptech.food.bot.slack.API

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
        filter { call -> call.request.path().startsWith(Bot.NEW_ORDER_PATH) }
    }

    val client = HttpClient(Apache)
    val botReplay = BotReplay()

    routing {
        get(Bot.HOME_PATH) { call.respondText(botReplay.hello(), contentType = ContentType.Text.Plain) }

        post(Bot.NEW_ORDER_PATH) {

            val id = call.receiveParameters()[API.TRIGGER_ID]

            if (!id.isNullOrBlank()) {
                // TODO: extract into API extension
                // TODO: pass type of modal and assemble it
                val response = client.call(API.VIEW_OPEN) {
                    method = HttpMethod.Post
                    header(API.HEADER, API.header(getToken()))
                    body = TextContent(getModal(withTriggerId = id), contentType = ContentType.Application.Json)
                }.response

                log.debug(response.call.receive())
            }

            call.respond(HttpStatusCode.OK, "")
        }

        post(Bot.USER_INTERACTIONS) {
            log.debug(call.receiveParameters().entries().toString())
            call.respond(HttpStatusCode.OK, "")
        }
    }
}

fun getToken() = System.getenv()[Bot.TOKEN] ?: ""

fun getModal(withTriggerId: String): String {
    // TODO: extract into separate class
    return """
        {
  "trigger_id": "$withTriggerId",
  "view": {
    "type": "modal",
    "callback_id": "modal-identifier",
    "title": {
      "type": "plain_text",
      "text": "Just a modal"
    },
    "submit": {
    	"type": "plain_text",
    	"text": "Submit",
    	"emoji": true
    },
    "close": {
    	"type": "plain_text",
    	"text": "Cancel",
    	"emoji": true
    },
     "blocks": ${getModalBody()}
  }
}
    """.trimIndent()
}

fun getModalBody(): String {
    // TODO: extract into separate class
    return """
        [
        	{
        		"type": "input",
                "block_id": "input1",
        		"element": {
        			"type": "plain_text_input",
        			"action_id": "sl_input",
        			"placeholder": {
        				"type": "plain_text",
        				"text": "Placeholder text for single-line input"
        			}
        		},
        		"label": {
        			"type": "plain_text",
        			"text": "Label"
        		},
        		"hint": {
        			"type": "plain_text",
        			"text": "Hint text"
        		}
        	},
        	{
        		"type": "input",
                "block_id": "input2",
        		"element": {
        			"type": "plain_text_input",
        			"action_id": "ml_input",
        			"multiline": true,
        			"placeholder": {
        				"type": "plain_text",
        				"text": "Placeholder text for multi-line input"
        			}
        		},
        		"label": {
        			"type": "plain_text",
        			"text": "Label"
        		},
        		"hint": {
        			"type": "plain_text",
        			"text": "Hint text"
        		}
        	}
        ]
    """.trimIndent()
}