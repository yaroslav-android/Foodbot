package team.uptech.food.bot.presentation.modals.models

import com.google.gson.annotations.SerializedName

class Modal {
  @SerializedName("trigger_id")
  var triggerId: String = ""
  @SerializedName("view")
  var view: View? = null

  fun view(block: View.() -> Unit) = View().apply(block)
}