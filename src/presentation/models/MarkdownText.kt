package team.uptech.food.bot.presentation.models

import com.google.gson.annotations.SerializedName

private const val TYPE = "mrkdwn"

class MarkdownText {
  @SerializedName("type")
  var type: String = TYPE
  @SerializedName("text")
  var text: String = ""
}