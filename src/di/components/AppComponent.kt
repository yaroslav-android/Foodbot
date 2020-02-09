package team.uptech.food.bot.di.components

import dagger.Component
import team.uptech.food.bot.EntryPoint
import team.uptech.food.bot.bot.BotReplay
import team.uptech.food.bot.data.Storage
import team.uptech.food.bot.di.modules.StorageModule
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class])
interface AppComponent {
  fun provideStorage(): Storage
  fun provideBotReplay(): BotReplay

  fun inject(application: EntryPoint)
}