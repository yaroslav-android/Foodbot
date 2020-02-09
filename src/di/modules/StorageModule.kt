package team.uptech.food.bot.di.modules

import dagger.Module
import dagger.Provides
import team.uptech.food.bot.bot.BotReplay
import team.uptech.food.bot.data.DataStorage
import team.uptech.food.bot.data.Storage
import javax.inject.Singleton

@Module
class StorageModule {

  @Singleton
  @Provides
  fun provideStorage(): Storage = DataStorage()

  @Singleton
  @Provides
  fun provideBotReplay(): BotReplay = BotReplay()

}