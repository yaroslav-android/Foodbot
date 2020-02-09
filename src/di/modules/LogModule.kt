package team.uptech.food.bot.di.modules

import dagger.Module
import dagger.Provides
import io.ktor.application.Application
import team.uptech.food.bot.utils.logs.DebugLogger
import team.uptech.food.bot.utils.logs.Logger
import javax.inject.Singleton

@Module
class LogModule {

  @Singleton
  @Provides
  fun provideLogger(application:Application): Logger = DebugLogger(application)
  
}