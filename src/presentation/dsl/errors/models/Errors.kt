package team.uptech.food.bot.presentation.dsl.errors.models

import com.google.gson.annotations.SerializedName
import team.uptech.food.bot.presentation.dsl.models.BuilderMarker

private const val RESPONSE_ERROR = "errors"

@BuilderMarker
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

  fun errors(block: MutableMap<String, String>.() -> Unit) = errors.apply(block)
}