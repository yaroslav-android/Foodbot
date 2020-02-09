package team.uptech.food.bot.di.components

import dagger.Subcomponent
import team.uptech.food.bot.EntryPoint
import team.uptech.food.bot.di.modules.NewOrderModule
import team.uptech.food.bot.di.scopes.PipelineScope

@PipelineScope
@Subcomponent(modules = [NewOrderModule::class])
interface NewOrderComponent {
  fun inject(application: EntryPoint)
}