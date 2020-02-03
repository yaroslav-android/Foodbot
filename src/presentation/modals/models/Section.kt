package team.uptech.food.bot.presentation.modals.models

import com.google.gson.annotations.SerializedName

private const val TYPE_INPUT = "input"
private const val TYPE_SECTION = "section"

open class Section {
  @SerializedName("type")
  var type: String = TYPE_SECTION
  @SerializedName("block_id")
  var blockId: String = ""
}

class SectionInput : Section() {
  init {
    type = TYPE_INPUT
  }

  @SerializedName("element")
  var element: Element? = null
  @SerializedName("label")
  var label: PlainText? = null
  @SerializedName("optional")
  var optional: Boolean = false

  fun element(block: Element.() -> Unit) = Element().apply(block).also { element = it }
  fun label(block: PlainText.() -> Unit) = PlainText().apply(block).also { label = it }
}

class SectionText : Section() {
  @SerializedName("text")
  var text: Text? = null

  fun text(block: Text.() -> Unit) = Text().apply(block).also { text = it }
}