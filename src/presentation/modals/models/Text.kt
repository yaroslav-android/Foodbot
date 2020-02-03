package team.uptech.food.bot.presentation.modals.models

import com.google.gson.annotations.SerializedName

private const val TYPE = "mrkdwn"

class Text {
  @SerializedName("type")
  var type: String = TYPE
  @SerializedName("text")
  var text: String = ""
}