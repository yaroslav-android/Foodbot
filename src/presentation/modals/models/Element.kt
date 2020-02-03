package team.uptech.food.bot.presentation.modals.models

import com.google.gson.annotations.SerializedName

private const val TYPE = "plain_text_input"

class Element {
  @SerializedName("type")
  var type: String = TYPE
  @SerializedName("action_id")
  var actionId: String = ""
  @SerializedName("multiline")
  var multiline: Boolean = true
  @SerializedName("placeholder")
  var placeholder: PlainText? = null

  fun placeholder(block: PlainText.() -> Unit) = PlainText().apply(block).also { placeholder = it }
}