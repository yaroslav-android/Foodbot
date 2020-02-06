package team.uptech.food.bot.presentation.messages.models

import com.google.gson.annotations.SerializedName
import team.uptech.food.bot.presentation.models.Section

class Message {
  @SerializedName("token")
  var token: String = ""
  @SerializedName("user")
  var userId: String = ""
  @SerializedName("channel")
  var channelId: String = ""
  @SerializedName("blocks")
  var blocks: MutableList<Section> = mutableListOf()

  fun blocks(block: MutableList<Section>.() -> Unit) = blocks.apply(block)
}