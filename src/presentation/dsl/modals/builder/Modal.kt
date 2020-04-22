package team.uptech.food.bot.presentation.dsl.modals.builder

import com.google.gson.annotations.SerializedName
import team.uptech.food.bot.presentation.dsl.models.BuilderMarker


@BuilderMarker
class Modal {
  @SerializedName("trigger_id")
  var triggerId: String = ""
  @SerializedName("view")
  var view: View? = null

  fun view(block: View.() -> Unit) = View()
    .apply(block).also { view = it }
}