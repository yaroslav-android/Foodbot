package team.uptech.food.bot.data.respones

import com.google.gson.annotations.SerializedName


data class Action(@SerializedName("action_id") val actionId: String, @SerializedName("value") val value: String)