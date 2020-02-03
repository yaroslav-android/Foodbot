package team.uptech.food.bot.presentation.modals.models

import com.google.gson.annotations.SerializedName
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance


const val TYPE_MODAL = "modal"

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
  var blocks: MutableList<Section> = mutableListOf()

  fun title(block: Title.() -> Unit) = Title().apply(block).also { title = it }
  fun submit(block: Submit.() -> Unit) = Submit().apply(block).also { submit = it }
  fun close(block: Close.() -> Unit) = Close().apply(block).also { close = it }
  fun blocks(block: MutableList<Section>.() -> Unit) = blocks.apply(block)

  /**
   * Use only within `blocks` only!
   */
  inline fun <reified T : Section> item(block: T.() -> Unit) =
    getInstance(T::class).apply(block).also { blocks.add(it) }

  fun <T : Section> getInstance(cls: KClass<T>) = cls.createInstance()
}