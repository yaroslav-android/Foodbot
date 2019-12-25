package team.uptech.food.bot

import io.ktor.application.*
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
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.request.path
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.css.*
import kotlinx.html.*
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/new_order") }
    }

    val client = HttpClient(Apache) {
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        post("/new_order") {
            val id = call.receiveParameters().get("trigger_id")
            call.respond("Let me try..")

            if (!id.isNullOrBlank()) {
                val response = client.call("https://slack.com/api/views.open") {
                    method = HttpMethod.Post
                    header("Authorization", "Bearer ${getToken()}")
                    body = TextContent(getModal(withTriggerId = id), contentType = ContentType.Application.Json)
                }.response

                log.debug(response.call.receive())
            }
        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }

        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.red
                }
                p {
                    fontSize = 2.em
                }
                rule("p.myclass") {
                    color = Color.blue
                }
            }
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

fun getToken(): String {
    TODO("not implemented")
}

fun getModal(withTriggerId: String): String {
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
    return """
        [
        	{
        		"type": "input",
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

fun FlowOrMetaDataContent.styleCss(builder: CSSBuilder.() -> Unit) {
    style(type = ContentType.Text.CSS.toString()) {
        +CSSBuilder().apply(builder).toString()
    }
}

fun CommonAttributeGroupFacade.style(builder: CSSBuilder.() -> Unit) {
    this.style = CSSBuilder().apply(builder).toString().trim()
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
