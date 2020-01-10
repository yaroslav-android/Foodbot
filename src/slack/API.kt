package team.uptech.food.bot.slack

object API {
    const val VIEW_OPEN = "https://slack.com/api/views.open"
    const val SEND_MSG = "https://slack.com/api/chat.postMessage"
    const val UPDATE_MSG = "https://slack.com/api/chat.update"

    const val HEADER = "Authorization"

    const val TRIGGER_ID = "trigger_id"
    const val CHANNEL_ID = "channel_id"
    const val USER_ID = "user_id"
    const val USER_NAME = "user_name"

    fun header(token: String) = "Bearer $token"
}