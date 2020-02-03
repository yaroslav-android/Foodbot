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

  fun title(block: Title.() -> Unit) = Title().apply(block)
  fun submit(block: Submit.() -> Unit) = Submit().apply(block)
  fun close(block: Close.() -> Unit) = Close().apply(block)
  fun blocks(block: Blocks.() -> Unit) = Blocks().apply(block)
}