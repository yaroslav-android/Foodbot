package team.uptech.food.bot.domain.order.model

/* TODO: use bitly to shorten links. But before shortening, parse Restaurant -> name */
data class MenuLink(
  val url: String
  // https://dev.bitly.com/v4_documentation.html#operation/createBitlink
)