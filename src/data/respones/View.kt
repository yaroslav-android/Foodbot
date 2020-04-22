package team.uptech.food.bot.data.respones

import com.google.gson.annotations.SerializedName


data class View(
  @SerializedName("callback_id") val callbackId: String
)
