package team.uptech.food.bot.di.components

import dagger.Component
import io.ktor.application.Application
import io.ktor.client.HttpClient
import team.uptech.food.bot.bot.BotReplay
import team.uptech.food.bot.data.Storage
import team.uptech.food.bot.di.modules.ApplicationModule
import team.uptech.food.bot.di.modules.LogModule
import team.uptech.food.bot.di.modules.StorageModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LogModule::class, StorageModule::class])
interface AppComponent {
  fun provideStorage(): Storage
  fun provideBotReplay(): BotReplay
  fun provideApplication(): Application
  fun provideHttpClient(): HttpClient
}