package team.uptech.food.bot.utils

import java.util.regex.Pattern

object Regex {

  /**
   * `<@U1234|user>` gives you id with `@`.
   */
  val SLACK_ID_REGEX = Pattern.compile("@[A-Za-z0-9]*")

  /**
   * Works for users as well as channels.
   */
  fun slackEscapedIdTemplate(id: String, name: String) = "<@$id|$name>"

  fun matchSlackId(id: String) = SLACK_ID_REGEX.matcher(id)
    .takeIf { it.matches() }
    ?.group()?.substring(1)

}