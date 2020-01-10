package team.uptech.food.bot.slack.interactions

import com.google.gson.annotations.SerializedName

// TODO: consider to drop it
data class UserInteractionPayload(
    val sender: Sender,
    @SerializedName("trigger_id")
    val triggerId: String,
    val channel: Channel
)

data class Sender(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String
)

data class Channel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)