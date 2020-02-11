package team.uptech.food.bot.domain.order

import team.uptech.food.bot.data.Storage
import javax.inject.Inject

class MakeOrderUseCaseImpl @Inject constructor(private val storage: Storage) : MakeOrderUseCase {

  companion object {
    private var nextOrderId = 1L
      get() {
        return field.also { nextOrderId += 1 }
      }
  }
}