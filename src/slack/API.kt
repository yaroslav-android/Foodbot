package team.uptech.food.bot.slack

object API {
    // Modals
    const val VIEW_OPEN = "https://slack.com/api/views.open"
    const val VIEW_UPD = "https://slack.com/api/views.update"

    // Messages
    const val SEND_MSG = "https://slack.com/api/chat.postMessage"
    const val UPDATE_MSG = "https://slack.com/api/chat.update"

    // Slash commands
    const val TRIGGER_ID = "trigger_id"
    const val CHANNEL_ID = "channel_id"
    const val USER_ID = "user_id"

    // Other
    const val USER_PROFILE = "https://slack.com/api/users.profile.get"

    const val HEADER = "Authorization"

    fun header(token: String) = "Bearer $token"
}