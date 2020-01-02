package team.uptech.food.bot.slack

object API {
    const val VIEW_OPEN = "https://slack.com/api/views.open"
    const val HEADER = "Authorization"

    const val TRIGGER_ID = "trigger_id"

    fun header(token: String) = "Bearer $token"
}