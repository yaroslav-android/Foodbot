package team.uptech.food.bot.presentation.errors.models

import com.google.gson.annotations.SerializedName

private const val RESPONSE_ERROR = "errors"

class Error {
  @SerializedName("response_action")
  var responseAction: String = RESPONSE_ERROR

  /**
   * key: `block_id`
   *
   * value: `error message`
   */
  @SerializedName("errors")
  val errors: MutableMap<String, String> = mutableMapOf()

  fun errors(block: MutableMap<String, String>.()->Unit) = errors.apply(block)
}