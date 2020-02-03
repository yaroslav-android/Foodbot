package team.uptech.food.bot.presentation.modals

import team.uptech.food.bot.presentation.modals.models.SectionInput

object ModalBuilder {

  fun assembleNewOrder(id: String) = NewOrderModal()
    .assemble {
      triggerId = id

      view {
        callbackId = "order_initiation"

        title {
          text = "New Order :stew:"
        }

        submit {
          text = "Submit"
        }

        close {
          text = "Cancel"
        }

        blocks {
          item<SectionInput> {
            blockId = "amount_of_ppl_block"

            element {
              multiline = false
              actionId = "amount_of_ppl_block_input"

              placeholder {
                text = "7"
              }
            }

            label {
              text = "Amount of people"
            }
          }

          item<SectionInput> {
            blockId = "time_picker_block"

            element {
              multiline = false
              actionId = "time_picker_block_input"

              placeholder {
                text = "12:00 pm"
              }
            }

            label {
              text = "Pick time"
            }
          }

          item<SectionInput> {
            blockId = "menu_link_block"
            optional = true

            element {
              multiline = false
              actionId = "menu_link_block_input"

              placeholder {
                text = "ubereats.com/uk-UA/kyiv/food-delivery/"
              }
            }

            label {
              text = "Menu"
            }
          }

          item<SectionInput> {
            blockId = "delivery_fee_block"

            element {
              multiline = false
              actionId = "delivery_fee_block_input"
              optional = true

              placeholder {
                text = "30"
              }
            }

            label {
              text = "Delivery fee"
            }
          }

          item<SectionInput> {
            blockId = "card_number_block"

            element {
              multiline = false
              actionId = "card_number_block_input"

              placeholder {
                text = "5175 0000 0000 0000"
              }
            }

            label {
              text = "Credit card (refund)"
            }
          }
        }
      }
    }
}