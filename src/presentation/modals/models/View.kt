package team.uptech.food.bot.presentation.modals.models

import com.google.gson.annotations.SerializedName


const val TYPE_MODAL = "modal"
const val TYPE_MSG = "message"

class View {
  @SerializedName("type")
  val type: String = TYPE_MODAL
  @SerializedName("callback_id")
  var callbackId: String = ""
  @SerializedName("title")
  var title: Title? = null
  @SerializedName("submit")
  var submit: Submit? = null
  @SerializedName("close")
  var close: Close? = null
  @SerializedName("blocks")
  var blocks: List<Section> = listOf()

  fun title(block: Title.() -> Unit) = Title().apply(block).also { title = it }
  fun submit(block: Submit.() -> Unit) = Submit().apply(block).also { submit = it }
  fun close(block: Close.() -> Unit) = Close().apply(block).also { close = it }
  fun blocks(block: MutableList<Section>.() -> Unit) = mutableListOf<Section>().apply(block).also { blocks = it }
}