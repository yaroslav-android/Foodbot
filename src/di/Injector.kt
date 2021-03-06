package team.uptech.food.bot.di

import io.ktor.application.Application
import team.uptech.food.bot.di.components.AppComponent
import team.uptech.food.bot.di.components.DaggerAppComponent
import team.uptech.food.bot.di.components.NewOrderComponent
import team.uptech.food.bot.di.modules.ApplicationModule

object Injector {
  private var applicationComponent: AppComponent? = null
  private var newOrderComponent: NewOrderComponent? = null

  fun injectApplication(application: Application): AppComponent? =
    if (applicationComponent != null) {
      applicationComponent
    } else {
      DaggerAppComponent.builder()
        .applicationModule(ApplicationModule(application))
        .build()
        .also { applicationComponent = it }
    }

  fun dropAppComponent() {
    applicationComponent = null
  }

  fun injectSubComponentNewOrder() =
    if (newOrderComponent != null) {
      newOrderComponent
    } else {
      applicationComponent?.plusNewOrderComponent()
        .also { newOrderComponent = it }
    }

  fun dropSubComponentNewOrder() {
    newOrderComponent = null
  }

}