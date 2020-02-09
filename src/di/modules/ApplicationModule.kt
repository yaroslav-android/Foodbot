package team.uptech.food.bot.di.modules

import dagger.Module
import dagger.Provides
import io.ktor.application.Application
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplication() = application

  @Provides
  @Singleton
  fun provideClient() = HttpClient(Apache)

}