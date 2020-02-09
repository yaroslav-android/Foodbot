package team.uptech.food.bot.di

import team.uptech.food.bot.di.components.AppComponent
import team.uptech.food.bot.di.components.DaggerAppComponent

object Injector {
  fun injectApplication(): AppComponent? = DaggerAppComponent.builder().build()
}