package team.uptech.food.bot.order.client

sealed class ClientAction {
    object Add : ClientAction()
    object Remove : ClientAction()
    object Reserve : ClientAction()
}