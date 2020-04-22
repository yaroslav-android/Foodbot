package team.uptech.food.bot.presentation.dsl.models

import com.google.gson.annotations.SerializedName

private const val TYPE = "mrkdwn"

@BuilderMarker
class MarkdownText {
  @SerializedName("type")
  var type: String = TYPE

  @SerializedName("text")
  var text: String = ""
}