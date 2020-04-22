package team.uptech.food.bot.presentation.dsl.models

import com.google.gson.annotations.SerializedName

private const val TYPE_INPUT = "plain_text_input"
private const val TYPE_BUTTON = "button"

@BuilderMarker
open class Element {
  @SerializedName("type")
  var type: String = TYPE_INPUT

  @SerializedName("action_id")
  var actionId: String = ""
}

class ElementInput : Element() {
  @SerializedName("multiline")
  var multiline: Boolean = true

  @SerializedName("placeholder")
  var placeholder: PlainText? = null

  fun placeholder(block: PlainText.() -> Unit) = PlainText().apply(block).also { placeholder = it }
}

class ElementButton : Element() {
  init {
    type = TYPE_BUTTON
  }

  @SerializedName("value")
  var value: String = ""

  @SerializedName("text")
  var text: PlainText? = null

  fun text(block: PlainText.() -> Unit) = PlainText().apply(block).also { text = it }
}