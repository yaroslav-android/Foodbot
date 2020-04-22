package team.uptech.food.bot.presentation.dsl.models

import com.google.gson.annotations.SerializedName

private const val TYPE_INPUT = "input"
private const val TYPE_SECTION = "section"
private const val TYPE_ACTIONS = "actions"

@BuilderMarker
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
  var element: ElementInput? = null

  @SerializedName("label")
  var label: PlainText? = null

  @SerializedName("optional")
  var optional: Boolean = false

  fun element(block: ElementInput.() -> Unit) = ElementInput().apply(block).also { element = it }
  fun label(block: PlainText.() -> Unit) = PlainText().apply(block).also { label = it }
}

class SectionText : Section() {
  @SerializedName("text")
  var text: MarkdownText? = null

  fun text(block: MarkdownText.() -> Unit) = MarkdownText().apply(block).also { text = it }
}

class SectionButton : Section() {
  init {
    type = TYPE_ACTIONS
  }

  @SerializedName("style")
  var style: String? = ButtonStyle.Default.style

  @SerializedName("elements")
  var elements: MutableList<Element> = mutableListOf()

  fun elements(block: MutableList<Element>.() -> Unit) = elements.apply(block)
}