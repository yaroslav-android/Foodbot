package team.uptech.food.bot.domain.order.model

import com.google.gson.annotations.SerializedName


data class Channel(
  @SerializedName("id")
  val id: String,
  @SerializedName("name")
  val name: String
)