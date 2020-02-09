package team.uptech.food.bot.di.modules

import dagger.Module
import dagger.Provides
import io.ktor.application.Application
import io.ktor.client.HttpClient
import team.uptech.food.bot.data.Storage
import team.uptech.food.bot.di.scopes.PipelineScope
import team.uptech.food.bot.domain.initiator.InitiatorUseCase
import team.uptech.food.bot.domain.initiator.InitiatorUseCaseImpl
import team.uptech.food.bot.domain.order.MakeOrderUseCase
import team.uptech.food.bot.domain.order.MakeOrderUseCaseImpl
import team.uptech.food.bot.presentation.new_order.NewOrderPresenter
import team.uptech.food.bot.presentation.new_order.NewOrderPresenterImpl

@Module
class NewOrderModule {

  @Provides
  @PipelineScope
  fun provideInitiatorUseCase(
    application: Application,
    client: HttpClient,
    storage: Storage
  ): InitiatorUseCase = InitiatorUseCaseImpl(application, client, storage)

  @Provides
  @PipelineScope
  fun provideMakeOrderUseCase(storage: Storage): MakeOrderUseCase = MakeOrderUseCaseImpl(storage)

  @Provides
  @PipelineScope
  fun provideNewOrderPresenter(
    application: Application,
    client: HttpClient,
    initiatorUseCase: InitiatorUseCase
  ): NewOrderPresenter =
    NewOrderPresenterImpl(application, client, initiatorUseCase)

}