package team.uptech.food.bot.data.respones

import com.google.gson.annotations.SerializedName


data class Payload(
  @SerializedName("view") val view: View,
  @SerializedName("user") val user: User,
  @SerializedName("actions") val actions: List<Action>,
  @SerializedName("trigger_id") val triggerId: String
) {
  companion object {
    const val KEY = "payload"
  }
}