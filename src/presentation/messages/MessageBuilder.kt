package team.uptech.food.bot.presentation.messages

import team.uptech.food.bot.presentation.models.ElementButton
import team.uptech.food.bot.presentation.models.SectionButton
import team.uptech.food.bot.presentation.models.SectionText
import team.uptech.food.bot.utils.extensions.block
import team.uptech.food.bot.utils.extensions.element

object MessageBuilder {
  fun assembleOrderSettings(
    token: String,
    userId: String,
    channelId: String = "CR1E4P198" /* temporary hardcode */
  ) = NewOrderSettingsMessage()
    .assemble {
      this.token = token
      this.userId = userId
      this.channelId = channelId

      blocks {
        block<SectionText> {
          text {
            text = "You scheduled the order. This is your control menu."
          }
        }

        block<SectionButton> {
          blockId = "update_order_actions"

          elements {
            element<ElementButton> {
              actionId = "update_order_status_cta"
              value = "update-order-status"

              text {
                text = "Update Order Status"
              }
            }

            element<ElementButton> {
              actionId = "update_order_time_cta"
              value = "update-order-time"

              text {
                text = "Update Order Time"
              }
            }
          }
        }
      }
    }
}