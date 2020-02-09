package team.uptech.food.bot.presentation.new_order

import io.ktor.application.ApplicationCall
import io.ktor.util.pipeline.PipelineContext

interface NewOrderPresenter {
  suspend fun startNewOrder(pipelineContext: PipelineContext<Unit, ApplicationCall>)
  suspend fun processNewOrder(
    pipelineContext: PipelineContext<Unit, ApplicationCall>,
    payload: String?
  )
}