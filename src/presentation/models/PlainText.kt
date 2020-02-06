package team.uptech.food.bot.presentation.models

import com.google.gson.annotations.SerializedName

private const val TYPE_PLAIN_TEXT = "plain_text"

class Title : PlainText()
class Submit : PlainText()
class Close : PlainText()

open class PlainText {
  @SerializedName("type")
  val type: String = TYPE_PLAIN_TEXT
  @SerializedName("text")
  var text: String = ""
  @SerializedName("emoji")
  var emoji: Boolean = true
}